package com.sqlplayground.engine.parser;

import com.sqlplayground.engine.lexer.Token;
import com.sqlplayground.engine.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() { return tokens.get(pos); }
    private Token consume() { return tokens.get(pos++); }
    private boolean check(TokenType type) { return peek().type == type; }

    private Token expect(TokenType type) {
        Token t = peek();
        if (t.type != type)
            throw new ParseException("Expected " + type + " but got " + t.type + " ('" + t.value + "') at pos " + t.pos);
        return consume();
    }

    private boolean match(TokenType... types) {
        for (TokenType t : types) {
            if (check(t)) { consume(); return true; }
        }
        return false;
    }

    public AstNode parse() {
        AstNode stmt = parseStatement();
        match(TokenType.SEMICOLON);
        return stmt;
    }

    private AstNode parseStatement() {
        TokenType tt = peek().type;
        if (tt == TokenType.SELECT) return parseSelect();
        if (tt == TokenType.INSERT) return parseInsert();
        if (tt == TokenType.CREATE) return parseCreate();
        if (tt == TokenType.DROP)   return parseDrop();
        if (tt == TokenType.DELETE) return parseDelete();
        if (tt == TokenType.UPDATE) return parseUpdate();
        throw new ParseException("Unexpected token: " + peek().value);
    }

    // ---- SELECT ----
    private AstNode parseSelect() {
        expect(TokenType.SELECT);
        boolean distinct = match(TokenType.DISTINCT);

        List<AstNode> columns = parseSelectColumns();
        expect(TokenType.FROM);
        AstNode from = parseTableRef();

        AstNode where = null;
        if (match(TokenType.WHERE)) where = parseExpression();

        List<AstNode> groupBy = new ArrayList<>();
        if (check(TokenType.GROUP)) {
            consume();
            expect(TokenType.BY);
            groupBy.add(parseExpression());
            while (match(TokenType.COMMA)) groupBy.add(parseExpression());
        }

        List<AstNode> orderBy = new ArrayList<>();
        if (check(TokenType.ORDER)) {
            consume();
            expect(TokenType.BY);
            orderBy.add(parseOrderItem());
            while (match(TokenType.COMMA)) orderBy.add(parseOrderItem());
        }

        Integer limit = null, offset = null;
        if (match(TokenType.LIMIT))  limit  = Integer.parseInt(expect(TokenType.NUMBER_LITERAL).value);
        if (match(TokenType.OFFSET)) offset = Integer.parseInt(expect(TokenType.NUMBER_LITERAL).value);

        return new AstNode.SelectStatement(distinct, columns, from, where, orderBy, groupBy, limit, offset);
    }

    private List<AstNode> parseSelectColumns() {
        List<AstNode> cols = new ArrayList<>();
        if (check(TokenType.STAR)) {
            consume();
            cols.add(new AstNode(AstNode.NodeType.WILDCARD, "*", null));
            return cols;
        }
        cols.add(parseSelectColumn());
        while (match(TokenType.COMMA)) cols.add(parseSelectColumn());
        return cols;
    }

    private AstNode parseSelectColumn() {
        AstNode expr = parseExpression();
        if (match(TokenType.AS)) {
            String alias = expect(TokenType.IDENTIFIER).value;
            return new AstNode.Alias(expr, alias);
        }
        return expr;
    }

    private AstNode parseTableRef() {
        String name = expect(TokenType.IDENTIFIER).value;
        AstNode ref = new AstNode(AstNode.NodeType.TABLE_REF, name, null);
        if (match(TokenType.AS) || check(TokenType.IDENTIFIER)) {
            String alias = expect(TokenType.IDENTIFIER).value;
            return new AstNode.Alias(ref, alias);
        }
        return ref;
    }

    private AstNode parseOrderItem() {
        AstNode expr = parseExpression();
        boolean asc = true;
        if (match(TokenType.DESC)) asc = false;
        else match(TokenType.ASC);
        return new AstNode.OrderItem(expr, asc);
    }

    // ---- INSERT ----
    private AstNode parseInsert() {
        expect(TokenType.INSERT);
        expect(TokenType.INTO);
        String table = expect(TokenType.IDENTIFIER).value;

        List<String> cols = new ArrayList<>();
        if (match(TokenType.LPAREN)) {
            cols.add(expect(TokenType.IDENTIFIER).value);
            while (match(TokenType.COMMA)) cols.add(expect(TokenType.IDENTIFIER).value);
            expect(TokenType.RPAREN);
        }

        expect(TokenType.VALUES);
        List<List<AstNode>> valueSets = new ArrayList<>();
        valueSets.add(parseValueList());
        while (match(TokenType.COMMA)) valueSets.add(parseValueList());

        return new AstNode.InsertStatement(table, cols, valueSets);
    }

    private List<AstNode> parseValueList() {
        expect(TokenType.LPAREN);
        List<AstNode> vals = new ArrayList<>();
        vals.add(parsePrimary());
        while (match(TokenType.COMMA)) vals.add(parsePrimary());
        expect(TokenType.RPAREN);
        return vals;
    }

    // ---- CREATE ----
    private AstNode parseCreate() {
        expect(TokenType.CREATE);
        expect(TokenType.TABLE);
        String table = expect(TokenType.IDENTIFIER).value;
        expect(TokenType.LPAREN);
        List<AstNode.ColumnDef> defs = new ArrayList<>();
        defs.add(parseColumnDef());
        while (match(TokenType.COMMA)) defs.add(parseColumnDef());
        expect(TokenType.RPAREN);
        return new AstNode.CreateTableStatement(table, defs);
    }

    private AstNode.ColumnDef parseColumnDef() {
        String name = expect(TokenType.IDENTIFIER).value;
        String type = consume().value.toUpperCase();
        if (check(TokenType.LPAREN)) { consume(); consume(); consume(); }
        boolean pk = false, notNull = false;
        while (check(TokenType.IDENTIFIER)) {
            String kw = peek().value.toUpperCase();
            if (kw.equals("PRIMARY")) { consume(); expect(TokenType.IDENTIFIER); pk = true; }
            else if (kw.equals("NOT")) { consume(); consume(); notNull = true; }
            else break;
        }
        return new AstNode.ColumnDef(name, type, pk, notNull);
    }

    // ---- DROP ----
    private AstNode parseDrop() {
        expect(TokenType.DROP);
        expect(TokenType.TABLE);
        return new AstNode.DropTableStatement(expect(TokenType.IDENTIFIER).value);
    }

    // ---- DELETE ----
    private AstNode parseDelete() {
        expect(TokenType.DELETE);
        expect(TokenType.FROM);
        String table = expect(TokenType.IDENTIFIER).value;
        AstNode where = null;
        if (match(TokenType.WHERE)) where = parseExpression();
        return new AstNode.DeleteStatement(table, where);
    }

    // ---- UPDATE ----
    private AstNode parseUpdate() {
        expect(TokenType.UPDATE);
        String table = expect(TokenType.IDENTIFIER).value;
        expect(TokenType.SET);
        List<AstNode.Assignment> assignments = new ArrayList<>();
        assignments.add(parseAssignment());
        while (match(TokenType.COMMA)) assignments.add(parseAssignment());
        AstNode where = null;
        if (match(TokenType.WHERE)) where = parseExpression();
        return new AstNode.UpdateStatement(table, assignments, where);
    }

    private AstNode.Assignment parseAssignment() {
        String col = expect(TokenType.IDENTIFIER).value;
        expect(TokenType.EQUALS);
        return new AstNode.Assignment(col, parseExpression());
    }

    // ---- Expressions (precedence climbing) ----
    private AstNode parseExpression() { return parseOr(); }

    private AstNode parseOr() {
        AstNode left = parseAnd();
        while (check(TokenType.OR)) {
            consume();
            left = new AstNode.BinaryExpr(left, "OR", parseAnd());
        }
        return left;
    }

    private AstNode parseAnd() {
        AstNode left = parseNot();
        while (check(TokenType.AND)) {
            consume();
            left = new AstNode.BinaryExpr(left, "AND", parseNot());
        }
        return left;
    }

    private AstNode parseNot() {
        if (match(TokenType.NOT)) {
            List<AstNode> ch = new ArrayList<>();
            ch.add(parseNot());
            return new AstNode(AstNode.NodeType.UNARY_EXPR, "NOT", ch);
        }
        return parseComparison();
    }

    private AstNode parseComparison() {
        AstNode left = parseAddSub();
        while (true) {
            String op = null;
            TokenType tt = peek().type;
            if      (tt == TokenType.EQUALS)     op = "=";
            else if (tt == TokenType.NOT_EQUALS)  op = "!=";
            else if (tt == TokenType.LT)          op = "<";
            else if (tt == TokenType.GT)          op = ">";
            else if (tt == TokenType.LTE)         op = "<=";
            else if (tt == TokenType.GTE)         op = ">=";
            else if (tt == TokenType.LIKE)        op = "LIKE";
            else if (tt == TokenType.IS)          op = "IS";
            else if (tt == TokenType.IN)          op = "IN";

            if (op == null) break;
            consume();

            if (op.equals("IN")) {
                expect(TokenType.LPAREN);
                List<AstNode> items = new ArrayList<>();
                items.add(parsePrimary());
                while (match(TokenType.COMMA)) items.add(parsePrimary());
                expect(TokenType.RPAREN);
                AstNode listNode = new AstNode(AstNode.NodeType.LITERAL, "LIST", items);
                left = new AstNode.BinaryExpr(left, "IN", listNode);
            } else {
                left = new AstNode.BinaryExpr(left, op, parseAddSub());
            }
        }
        return left;
    }

    private AstNode parseAddSub() {
        AstNode left = parseMulDiv();
        while (check(TokenType.PLUS) || check(TokenType.MINUS)) {
            String op = consume().value;
            left = new AstNode.BinaryExpr(left, op, parseMulDiv());
        }
        return left;
    }

    private AstNode parseMulDiv() {
        AstNode left = parsePrimary();
        while (check(TokenType.STAR) || check(TokenType.SLASH) || check(TokenType.PERCENT)) {
            String op = consume().value;
            left = new AstNode.BinaryExpr(left, op, parsePrimary());
        }
        return left;
    }

    // ---- Java 17 safe: no switch expression with return ----
    private AstNode parsePrimary() {
        Token t = peek();

        if (t.type == TokenType.NUMBER_LITERAL) {
            consume();
            if (t.value.contains(".")) return new AstNode.Literal(Double.parseDouble(t.value));
            else                       return new AstNode.Literal(Long.parseLong(t.value));
        }

        if (t.type == TokenType.STRING_LITERAL) {
            consume();
            return new AstNode.Literal(t.value);
        }

        if (t.type == TokenType.TRUE)  { consume(); return new AstNode.Literal(true);  }
        if (t.type == TokenType.FALSE) { consume(); return new AstNode.Literal(false); }
        if (t.type == TokenType.NULL)  { consume(); return new AstNode.Literal(null);  }

        if (t.type == TokenType.STAR) {
            consume();
            return new AstNode(AstNode.NodeType.WILDCARD, "*", null);
        }

        if (t.type == TokenType.LPAREN) {
            consume();
            AstNode expr = parseExpression();
            expect(TokenType.RPAREN);
            return expr;
        }

        if (t.type == TokenType.IDENTIFIER) {
            String name = consume().value;

            // function call: name(...)
            if (check(TokenType.LPAREN)) {
                consume();
                List<AstNode> args = new ArrayList<>();
                if (!check(TokenType.RPAREN)) {
                    if (check(TokenType.STAR)) {
                        consume();
                        args.add(new AstNode(AstNode.NodeType.WILDCARD, "*", null));
                    } else {
                        args.add(parseExpression());
                        while (match(TokenType.COMMA)) args.add(parseExpression());
                    }
                }
                expect(TokenType.RPAREN);
                return new AstNode.FunctionCall(name.toUpperCase(), args);
            }

            // qualified column: table.column
            if (check(TokenType.DOT)) {
                consume();
                String col = expect(TokenType.IDENTIFIER).value;
                return new AstNode.ColumnRef(name, col);
            }

            return new AstNode.ColumnRef(null, name);
        }

        throw new ParseException("Unexpected token in expression: " + t.value + " (" + t.type + ")");
    }

    public static class ParseException extends RuntimeException {
        public ParseException(String msg) { super(msg); }
    }
}
