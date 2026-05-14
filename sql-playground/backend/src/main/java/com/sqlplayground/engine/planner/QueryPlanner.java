package com.sqlplayground.engine.planner;

import com.sqlplayground.engine.parser.AstNode;
import com.sqlplayground.storage.InMemoryDatabase;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class QueryPlanner {

    private final InMemoryDatabase db;

    public QueryPlanner(InMemoryDatabase db) {
        this.db = db;
    }

    public PlanNode plan(AstNode ast) {
        if (ast.type == AstNode.NodeType.SELECT_STMT)
            return planSelect((AstNode.SelectStatement) ast);

        if (ast.type == AstNode.NodeType.INSERT_STMT) {
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("table", ((AstNode.InsertStatement) ast).tableName);
            return new PlanNode("INSERT", "Insert rows into table", Collections.emptyList(), stats);
        }
        if (ast.type == AstNode.NodeType.CREATE_TABLE_STMT) {
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("table", ((AstNode.CreateTableStatement) ast).tableName);
            return new PlanNode("CREATE", "Create new table", Collections.emptyList(), stats);
        }
        if (ast.type == AstNode.NodeType.DROP_TABLE_STMT) {
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("table", ((AstNode.DropTableStatement) ast).tableName);
            return new PlanNode("DROP", "Drop table", Collections.emptyList(), stats);
        }
        if (ast.type == AstNode.NodeType.DELETE_STMT) {
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("table", ((AstNode.DeleteStatement) ast).tableName);
            return new PlanNode("DELETE", "Delete matching rows", Collections.emptyList(), stats);
        }
        if (ast.type == AstNode.NodeType.UPDATE_STMT) {
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("table", ((AstNode.UpdateStatement) ast).tableName);
            return new PlanNode("UPDATE", "Update matching rows", Collections.emptyList(), stats);
        }

        return new PlanNode("UNKNOWN", "", Collections.emptyList(), Collections.emptyMap());
    }

    private PlanNode planSelect(AstNode.SelectStatement stmt) {
        String tableName = resolveTableName(stmt.from);
        int tableSize = db.tableExists(tableName) ? db.getTable(tableName).getRows().size() : 0;

        // SEQ_SCAN
        Map<String, Object> scanStats = new LinkedHashMap<>();
        scanStats.put("table", tableName);
        scanStats.put("rows", tableSize);
        scanStats.put("cost", tableSize);
        PlanNode node = new PlanNode("SEQ_SCAN", "Full table scan", Collections.emptyList(), scanStats);

        // FILTER
        if (stmt.where != null) {
            int outRows = (int)(tableSize * 0.3);
            Map<String, Object> filterStats = new LinkedHashMap<>();
            filterStats.put("condition", describeExpr(stmt.where));
            filterStats.put("est_rows", outRows);
            filterStats.put("cost", tableSize);
            node = new PlanNode("FILTER", "WHERE " + describeExpr(stmt.where),
                Collections.singletonList(node), filterStats);
        }

        // HASH_AGG
        if (!stmt.groupBy.isEmpty()) {
            List<String> groupCols = stmt.groupBy.stream()
                .map(this::describeExpr).collect(Collectors.toList());
            Map<String, Object> aggStats = new LinkedHashMap<>();
            aggStats.put("group_by", groupCols);
            aggStats.put("cost", tableSize / 2);
            node = new PlanNode("HASH_AGG", "Group + aggregate",
                Collections.singletonList(node), aggStats);
        }

        // SORT
        if (!stmt.orderBy.isEmpty()) {
            List<String> sortKeys = new ArrayList<>();
            for (AstNode o : stmt.orderBy) {
                AstNode.OrderItem oi = (AstNode.OrderItem) o;
                sortKeys.add(describeExpr(oi.expr) + (oi.ascending ? " ASC" : " DESC"));
            }
            Map<String, Object> sortStats = new LinkedHashMap<>();
            sortStats.put("keys", sortKeys);
            sortStats.put("cost", (int)(tableSize * Math.log(tableSize + 1)));
            node = new PlanNode("SORT", "Order results",
                Collections.singletonList(node), sortStats);
        }

        // LIMIT
        if (stmt.limit != null) {
            Map<String, Object> limitStats = new LinkedHashMap<>();
            limitStats.put("limit", stmt.limit);
            limitStats.put("offset", stmt.offset != null ? stmt.offset : 0);
            limitStats.put("cost", 1);
            node = new PlanNode("LIMIT", "Restrict output rows",
                Collections.singletonList(node), limitStats);
        }

        // PROJECT
        List<String> projCols = stmt.columns.stream()
            .map(this::describeExpr).collect(Collectors.toList());
        Map<String, Object> projStats = new LinkedHashMap<>();
        projStats.put("columns", projCols);
        projStats.put("cost", 1);
        node = new PlanNode("PROJECT", "Select columns",
            Collections.singletonList(node), projStats);

        return node;
    }

    private String describeExpr(AstNode node) {
        if (node instanceof AstNode.ColumnRef) {
            AstNode.ColumnRef cr = (AstNode.ColumnRef) node;
            return cr.table != null ? cr.table + "." + cr.column : cr.column;
        }
        if (node instanceof AstNode.Literal) {
            AstNode.Literal lit = (AstNode.Literal) node;
            return lit.literalValue == null ? "NULL" : lit.literalValue.toString();
        }
        if (node instanceof AstNode.BinaryExpr) {
            AstNode.BinaryExpr be = (AstNode.BinaryExpr) node;
            return describeExpr(be.left) + " " + be.operator + " " + describeExpr(be.right);
        }
        if (node instanceof AstNode.FunctionCall) {
            AstNode.FunctionCall fn = (AstNode.FunctionCall) node;
            String args = fn.args.stream().map(this::describeExpr)
                .collect(Collectors.joining(", "));
            return fn.funcName + "(" + args + ")";
        }
        if (node instanceof AstNode.Alias) {
            AstNode.Alias a = (AstNode.Alias) node;
            return describeExpr(a.inner) + " AS " + a.alias;
        }
        if (node instanceof AstNode.OrderItem) {
            AstNode.OrderItem oi = (AstNode.OrderItem) node;
            return describeExpr(oi.expr) + (oi.ascending ? " ASC" : " DESC");
        }
        if (node.type == AstNode.NodeType.WILDCARD) return "*";
        return node.value;
    }

    private String resolveTableName(AstNode from) {
        if (from == null) return "?";
        if (from.type == AstNode.NodeType.ALIAS) return resolveTableName(from.children.get(0));
        return from.value;
    }

    // ---- Plain class instead of record for Java 17 compatibility ----
    public static class PlanNode {
        private final String operation;
        private final String description;
        private final List<PlanNode> children;
        private final Map<String, Object> stats;

        public PlanNode(String operation, String description,
                        List<PlanNode> children, Map<String, Object> stats) {
            this.operation = operation;
            this.description = description;
            this.children = children;
            this.stats = stats;
        }

        public String getOperation()      { return operation; }
        public String getDescription()    { return description; }
        public List<PlanNode> getChildren(){ return children; }
        public Map<String, Object> getStats(){ return stats; }
    }
}
