package com.sqlplayground.engine.lexer;

import java.util.*;

public class Lexer {

    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("SELECT", TokenType.SELECT);
        KEYWORDS.put("FROM", TokenType.FROM);
        KEYWORDS.put("WHERE", TokenType.WHERE);
        KEYWORDS.put("AND", TokenType.AND);
        KEYWORDS.put("OR", TokenType.OR);
        KEYWORDS.put("NOT", TokenType.NOT);
        KEYWORDS.put("INSERT", TokenType.INSERT);
        KEYWORDS.put("INTO", TokenType.INTO);
        KEYWORDS.put("VALUES", TokenType.VALUES);
        KEYWORDS.put("CREATE", TokenType.CREATE);
        KEYWORDS.put("TABLE", TokenType.TABLE);
        KEYWORDS.put("DROP", TokenType.DROP);
        KEYWORDS.put("DELETE", TokenType.DELETE);
        KEYWORDS.put("UPDATE", TokenType.UPDATE);
        KEYWORDS.put("SET", TokenType.SET);
        KEYWORDS.put("ORDER", TokenType.ORDER);
        KEYWORDS.put("BY", TokenType.BY);
        KEYWORDS.put("ASC", TokenType.ASC);
        KEYWORDS.put("DESC", TokenType.DESC);
        KEYWORDS.put("LIMIT", TokenType.LIMIT);
        KEYWORDS.put("OFFSET", TokenType.OFFSET);
        KEYWORDS.put("JOIN", TokenType.JOIN);
        KEYWORDS.put("INNER", TokenType.INNER);
        KEYWORDS.put("LEFT", TokenType.LEFT);
        KEYWORDS.put("RIGHT", TokenType.RIGHT);
        KEYWORDS.put("ON", TokenType.ON);
        KEYWORDS.put("GROUP", TokenType.GROUP);
        KEYWORDS.put("HAVING", TokenType.HAVING);
        KEYWORDS.put("DISTINCT", TokenType.DISTINCT);
        KEYWORDS.put("AS", TokenType.AS);
        KEYWORDS.put("NULL", TokenType.NULL);
        KEYWORDS.put("IS", TokenType.IS);
        KEYWORDS.put("IN", TokenType.IN);
        KEYWORDS.put("LIKE", TokenType.LIKE);
        KEYWORDS.put("BETWEEN", TokenType.BETWEEN);
        KEYWORDS.put("INTEGER", TokenType.INTEGER);
        KEYWORDS.put("VARCHAR", TokenType.VARCHAR);
        KEYWORDS.put("BOOLEAN", TokenType.BOOLEAN);
        KEYWORDS.put("DOUBLE", TokenType.DOUBLE);
        KEYWORDS.put("TEXT", TokenType.TEXT);
        KEYWORDS.put("TRUE", TokenType.TRUE);
        KEYWORDS.put("FALSE", TokenType.FALSE);
    }

    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            skipWhitespace();
            if (pos >= input.length()) break;

            char c = input.charAt(pos);

            if (c == '-' && pos + 1 < input.length() && input.charAt(pos + 1) == '-') {
                skipLineComment();
                continue;
            }

            if (Character.isLetter(c) || c == '_') {
                tokens.add(readIdentifierOrKeyword());
            } else if (Character.isDigit(c) || (c == '-' && pos + 1 < input.length() && Character.isDigit(input.charAt(pos + 1)))) {
                tokens.add(readNumber());
            } else if (c == '\'' || c == '"') {
                tokens.add(readString());
            } else {
                tokens.add(readSymbol());
            }
        }
        tokens.add(new Token(TokenType.EOF, "", pos));
        return tokens;
    }

    private void skipWhitespace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) pos++;
    }

    private void skipLineComment() {
        while (pos < input.length() && input.charAt(pos) != '\n') pos++;
    }

    private Token readIdentifierOrKeyword() {
        int start = pos;
        while (pos < input.length() && (Character.isLetterOrDigit(input.charAt(pos)) || input.charAt(pos) == '_')) pos++;
        String word = input.substring(start, pos);
        TokenType type = KEYWORDS.getOrDefault(word.toUpperCase(), TokenType.IDENTIFIER);
        return new Token(type, word, start);
    }

    private Token readNumber() {
        int start = pos;
        if (input.charAt(pos) == '-') pos++;
        while (pos < input.length() && (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.')) pos++;
        return new Token(TokenType.NUMBER_LITERAL, input.substring(start, pos), start);
    }

    private Token readString() {
        char quote = input.charAt(pos);
        int start = pos++;
        while (pos < input.length() && input.charAt(pos) != quote) {
            if (input.charAt(pos) == '\\') pos++;
            pos++;
        }
        pos++;
        return new Token(TokenType.STRING_LITERAL, input.substring(start + 1, pos - 1), start);
    }

    private Token readSymbol() {
        int start = pos;
        char c = input.charAt(pos++);
        switch (c) {
            case '(': return new Token(TokenType.LPAREN, "(", start);
            case ')': return new Token(TokenType.RPAREN, ")", start);
            case ',': return new Token(TokenType.COMMA, ",", start);
            case ';': return new Token(TokenType.SEMICOLON, ";", start);
            case '.': return new Token(TokenType.DOT, ".", start);
            case '+': return new Token(TokenType.PLUS, "+", start);
            case '*': return new Token(TokenType.STAR, "*", start);
            case '/': return new Token(TokenType.SLASH, "/", start);
            case '%': return new Token(TokenType.PERCENT, "%", start);
            case '=': return new Token(TokenType.EQUALS, "=", start);
            case '<':
                if (pos < input.length() && input.charAt(pos) == '=') { pos++; return new Token(TokenType.LTE, "<=", start); }
                if (pos < input.length() && input.charAt(pos) == '>') { pos++; return new Token(TokenType.NOT_EQUALS, "<>", start); }
                return new Token(TokenType.LT, "<", start);
            case '>':
                if (pos < input.length() && input.charAt(pos) == '=') { pos++; return new Token(TokenType.GTE, ">=", start); }
                return new Token(TokenType.GT, ">", start);
            case '!':
                if (pos < input.length() && input.charAt(pos) == '=') { pos++; return new Token(TokenType.NOT_EQUALS, "!=", start); }
                return new Token(TokenType.UNKNOWN, "!", start);
            case '-': return new Token(TokenType.MINUS, "-", start);
            default: return new Token(TokenType.UNKNOWN, String.valueOf(c), start);
        }
    }
}
