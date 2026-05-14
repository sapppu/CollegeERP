package com.sqlplayground.engine.parser;

import java.util.ArrayList;
import java.util.List;

public class AstNode {

    public enum NodeType {
        SELECT_STMT, INSERT_STMT, CREATE_TABLE_STMT, DROP_TABLE_STMT, DELETE_STMT, UPDATE_STMT,
        COLUMN_REF, LITERAL, BINARY_EXPR, UNARY_EXPR, WILDCARD,
        TABLE_REF, ALIAS, ORDER_ITEM, COLUMN_DEF, FUNCTION_CALL
    }

    public final NodeType type;
    public final String value;
    public final List<AstNode> children;

    public AstNode(NodeType type, String value, List<AstNode> children) {
        this.type = type;
        this.value = value;
        this.children = children != null ? children : new ArrayList<>();
    }

    // ---- Statement nodes ----

    public static class SelectStatement extends AstNode {
        public final boolean distinct;
        public final List<AstNode> columns;
        public final AstNode from;
        public final AstNode where;
        public final List<AstNode> orderBy;
        public final List<AstNode> groupBy;
        public final Integer limit;
        public final Integer offset;

        public SelectStatement(boolean distinct, List<AstNode> columns, AstNode from,
                               AstNode where, List<AstNode> orderBy, List<AstNode> groupBy,
                               Integer limit, Integer offset) {
            super(NodeType.SELECT_STMT, "SELECT", null);
            this.distinct = distinct;
            this.columns = columns;
            this.from = from;
            this.where = where;
            this.orderBy = orderBy;
            this.groupBy = groupBy;
            this.limit = limit;
            this.offset = offset;
        }
    }

    public static class InsertStatement extends AstNode {
        public final String tableName;
        public final List<String> columns;
        public final List<List<AstNode>> valueSets;

        public InsertStatement(String tableName, List<String> columns, List<List<AstNode>> valueSets) {
            super(NodeType.INSERT_STMT, "INSERT", null);
            this.tableName = tableName;
            this.columns = columns;
            this.valueSets = valueSets;
        }
    }

    public static class CreateTableStatement extends AstNode {
        public final String tableName;
        public final List<ColumnDef> columnDefs;

        public CreateTableStatement(String tableName, List<ColumnDef> columnDefs) {
            super(NodeType.CREATE_TABLE_STMT, "CREATE TABLE", null);
            this.tableName = tableName;
            this.columnDefs = columnDefs;
        }
    }

    public static class DropTableStatement extends AstNode {
        public final String tableName;

        public DropTableStatement(String tableName) {
            super(NodeType.DROP_TABLE_STMT, "DROP TABLE", null);
            this.tableName = tableName;
        }
    }

    public static class DeleteStatement extends AstNode {
        public final String tableName;
        public final AstNode where;

        public DeleteStatement(String tableName, AstNode where) {
            super(NodeType.DELETE_STMT, "DELETE", null);
            this.tableName = tableName;
            this.where = where;
        }
    }

    public static class UpdateStatement extends AstNode {
        public final String tableName;
        public final List<Assignment> assignments;
        public final AstNode where;

        public UpdateStatement(String tableName, List<Assignment> assignments, AstNode where) {
            super(NodeType.UPDATE_STMT, "UPDATE", null);
            this.tableName = tableName;
            this.assignments = assignments;
            this.where = where;
        }
    }

    // ---- Expression nodes ----

    public static class ColumnRef extends AstNode {
        public final String table;
        public final String column;

        public ColumnRef(String table, String column) {
            super(NodeType.COLUMN_REF, column, null);
            this.table = table;
            this.column = column;
        }
    }

    public static class Literal extends AstNode {
        public final Object literalValue;

        public Literal(Object value) {
            super(NodeType.LITERAL, String.valueOf(value), null);
            this.literalValue = value;
        }
    }

    public static class BinaryExpr extends AstNode {
        public final AstNode left;
        public final String operator;
        public final AstNode right;

        public BinaryExpr(AstNode left, String operator, AstNode right) {
            super(NodeType.BINARY_EXPR, operator, buildList(left, right));
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        private static List<AstNode> buildList(AstNode a, AstNode b) {
            List<AstNode> l = new ArrayList<>();
            l.add(a);
            l.add(b);
            return l;
        }
    }

    public static class FunctionCall extends AstNode {
        public final String funcName;
        public final List<AstNode> args;

        public FunctionCall(String funcName, List<AstNode> args) {
            super(NodeType.FUNCTION_CALL, funcName, args);
            this.funcName = funcName;
            this.args = args;
        }
    }

    public static class OrderItem extends AstNode {
        public final AstNode expr;
        public final boolean ascending;

        public OrderItem(AstNode expr, boolean ascending) {
            super(NodeType.ORDER_ITEM, ascending ? "ASC" : "DESC", buildList(expr));
            this.expr = expr;
            this.ascending = ascending;
        }

        private static List<AstNode> buildList(AstNode e) {
            List<AstNode> l = new ArrayList<>();
            l.add(e);
            return l;
        }
    }

    public static class ColumnDef extends AstNode {
        public final String columnName;
        public final String dataType;
        public final boolean primaryKey;
        public final boolean notNull;

        public ColumnDef(String columnName, String dataType, boolean primaryKey, boolean notNull) {
            super(NodeType.COLUMN_DEF, columnName, null);
            this.columnName = columnName;
            this.dataType = dataType;
            this.primaryKey = primaryKey;
            this.notNull = notNull;
        }
    }

    // ---- Alias node — used for "expr AS name" in SELECT and FROM ----
    public static class Alias extends AstNode {
        public final AstNode inner;
        public final String alias;

        public Alias(AstNode inner, String alias) {
            super(NodeType.ALIAS, alias, buildList(inner));
            this.inner = inner;
            this.alias = alias;
        }

        private static List<AstNode> buildList(AstNode e) {
            List<AstNode> l = new ArrayList<>();
            l.add(e);
            return l;
        }
    }

    public static class Assignment {
        public final String column;
        public final AstNode value;

        public Assignment(String column, AstNode value) {
            this.column = column;
            this.value = value;
        }
    }
}
