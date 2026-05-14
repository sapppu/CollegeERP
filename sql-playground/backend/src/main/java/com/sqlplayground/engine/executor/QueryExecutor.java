package com.sqlplayground.engine.executor;

import com.sqlplayground.engine.parser.AstNode;
import com.sqlplayground.model.Table;
import com.sqlplayground.storage.InMemoryDatabase;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class QueryExecutor {

    private final InMemoryDatabase db;

    public QueryExecutor(InMemoryDatabase db) {
        this.db = db;
    }

    public QueryResult execute(AstNode ast) {
        if (ast.type == AstNode.NodeType.SELECT_STMT)
            return executeSelect((AstNode.SelectStatement) ast);
        if (ast.type == AstNode.NodeType.INSERT_STMT)
            return executeInsert((AstNode.InsertStatement) ast);
        if (ast.type == AstNode.NodeType.CREATE_TABLE_STMT)
            return executeCreate((AstNode.CreateTableStatement) ast);
        if (ast.type == AstNode.NodeType.DROP_TABLE_STMT)
            return executeDrop((AstNode.DropTableStatement) ast);
        if (ast.type == AstNode.NodeType.DELETE_STMT)
            return executeDelete((AstNode.DeleteStatement) ast);
        if (ast.type == AstNode.NodeType.UPDATE_STMT)
            return executeUpdate((AstNode.UpdateStatement) ast);
        throw new ExecutionException("Unknown statement type: " + ast.type);
    }

    // ---- SELECT ----
    private QueryResult executeSelect(AstNode.SelectStatement stmt) {
        String tableName = resolveTableName(stmt.from);
        Table table = db.getTable(tableName);

        List<Map<String, Object>> rows = new ArrayList<>(table.getRows());

        if (stmt.where != null)
            rows = rows.stream()
                .filter(r -> isTruthy(evalExpr(stmt.where, r)))
                .collect(Collectors.toList());

        if (!stmt.groupBy.isEmpty())
            rows = groupBy(rows, stmt.groupBy, stmt.columns);

        if (!stmt.orderBy.isEmpty())
            rows = sortRows(rows, stmt.orderBy);

        if (stmt.offset != null)
            rows = rows.stream().skip(stmt.offset).collect(Collectors.toList());

        if (stmt.limit != null)
            rows = rows.stream().limit(stmt.limit).collect(Collectors.toList());

        List<String> colNames = resolveColumnNames(stmt.columns, table);
        List<Map<String, Object>> projected = projectRows(rows, stmt.columns, table, colNames);

        if (stmt.distinct)
            projected = distinct(projected);

        return new QueryResult(colNames, projected, projected.size() + " row(s) returned");
    }

    private List<Map<String, Object>> groupBy(List<Map<String, Object>> rows,
                                               List<AstNode> groupByCols,
                                               List<AstNode> selectCols) {
        Map<String, List<Map<String, Object>>> groups = new LinkedHashMap<>();
        for (Map<String, Object> row : rows) {
            StringBuilder keyBuilder = new StringBuilder();
            for (AstNode g : groupByCols) {
                keyBuilder.append(evalExpr(g, row)).append("|");
            }
            String key = keyBuilder.toString();
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(row);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (List<Map<String, Object>> group : groups.values()) {
            Map<String, Object> aggRow = new LinkedHashMap<>(group.get(0));
            for (AstNode col : selectCols) {
                if (col instanceof AstNode.FunctionCall) {
                    AstNode.FunctionCall fn = (AstNode.FunctionCall) col;
                    aggRow.put(fn.funcName + "(*)", evalAggregate(fn, group));
                }
            }
            result.add(aggRow);
        }
        return result;
    }

    private Object evalAggregate(AstNode.FunctionCall fn, List<Map<String, Object>> group) {
        if ("COUNT".equals(fn.funcName)) return (long) group.size();
        if ("SUM".equals(fn.funcName))
            return group.stream().mapToDouble(r -> toDouble(evalExpr(fn.args.get(0), r))).sum();
        if ("AVG".equals(fn.funcName))
            return group.stream().mapToDouble(r -> toDouble(evalExpr(fn.args.get(0), r))).average().orElse(0);
        if ("MAX".equals(fn.funcName))
            return group.stream().mapToDouble(r -> toDouble(evalExpr(fn.args.get(0), r))).max().orElse(0);
        if ("MIN".equals(fn.funcName))
            return group.stream().mapToDouble(r -> toDouble(evalExpr(fn.args.get(0), r))).min().orElse(0);
        return null;
    }

    private List<Map<String, Object>> sortRows(List<Map<String, Object>> rows, List<AstNode> orderItems) {
        Comparator<Map<String, Object>> comp = null;
        for (AstNode item : orderItems) {
            AstNode.OrderItem oi = (AstNode.OrderItem) item;
            Comparator<Map<String, Object>> c = Comparator.comparing(
                r -> toComparable(evalExpr(oi.expr, r)),
                Comparator.nullsLast(Comparator.naturalOrder())
            );
            if (!oi.ascending) c = c.reversed();
            comp = (comp == null) ? c : comp.thenComparing(c);
        }
        if (comp != null) rows.sort(comp);
        return rows;
    }

    private List<String> resolveColumnNames(List<AstNode> cols, Table table) {
        if (cols.size() == 1 && cols.get(0).type == AstNode.NodeType.WILDCARD) {
            return table.columns.stream().map(Table.Column::getName).collect(Collectors.toList());
        }
        return cols.stream().map(this::colLabel).collect(Collectors.toList());
    }

    private String colLabel(AstNode node) {
        if (node instanceof AstNode.ColumnRef)   return ((AstNode.ColumnRef) node).column;
        if (node instanceof AstNode.Alias)        return ((AstNode.Alias) node).alias;
        if (node instanceof AstNode.FunctionCall) {
            AstNode.FunctionCall fn = (AstNode.FunctionCall) node;
            String arg = fn.args.isEmpty() ? "*" : colLabel(fn.args.get(0));
            return fn.funcName + "(" + arg + ")";
        }
        return node.value;
    }

    private List<Map<String, Object>> projectRows(List<Map<String, Object>> rows,
                                                   List<AstNode> cols,
                                                   Table table,
                                                   List<String> labels) {
        if (cols.size() == 1 && cols.get(0).type == AstNode.NodeType.WILDCARD) return rows;

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Map<String, Object> projected = new LinkedHashMap<>();
            for (int i = 0; i < cols.size(); i++) {
                AstNode col = cols.get(i);
                AstNode expr = (col instanceof AstNode.Alias)
                    ? ((AstNode.Alias) col).inner
                    : col;
                projected.put(labels.get(i), evalExpr(expr, row));
            }
            result.add(projected);
        }
        return result;
    }

    private List<Map<String, Object>> distinct(List<Map<String, Object>> rows) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (Map<String, Object> row : rows) {
            if (seen.add(row.toString())) result.add(row);
        }
        return result;
    }

    // ---- INSERT ----
    private QueryResult executeInsert(AstNode.InsertStatement stmt) {
        Table table = db.getTable(stmt.tableName);
        int count = 0;
        for (List<AstNode> vals : stmt.valueSets) {
            Map<String, Object> row = new LinkedHashMap<>();
            for (int i = 0; i < stmt.columns.size(); i++)
                row.put(stmt.columns.get(i), evalExpr(vals.get(i), Collections.emptyMap()));
            if (stmt.columns.isEmpty()) {
                for (int i = 0; i < table.columns.size() && i < vals.size(); i++)
                    row.put(table.columns.get(i).getName(), evalExpr(vals.get(i), Collections.emptyMap()));
            }
            table.insertRow(row);
            count++;
        }
        return new QueryResult(Collections.emptyList(), Collections.emptyList(), count + " row(s) inserted");
    }

    // ---- CREATE ----
    private QueryResult executeCreate(AstNode.CreateTableStatement stmt) {
        List<Table.Column> cols = new ArrayList<>();
        for (AstNode.ColumnDef d : stmt.columnDefs)
            cols.add(new Table.Column(d.columnName, d.dataType, d.primaryKey, d.notNull));
        db.createTable(new Table(stmt.tableName, cols));
        return new QueryResult(Collections.emptyList(), Collections.emptyList(),
            "Table '" + stmt.tableName + "' created");
    }

    // ---- DROP ----
    private QueryResult executeDrop(AstNode.DropTableStatement stmt) {
        db.dropTable(stmt.tableName);
        return new QueryResult(Collections.emptyList(), Collections.emptyList(),
            "Table '" + stmt.tableName + "' dropped");
    }

    // ---- DELETE ----
    private QueryResult executeDelete(AstNode.DeleteStatement stmt) {
        Table table = db.getTable(stmt.tableName);
        int deleted = table.deleteRows(
            row -> stmt.where == null || isTruthy(evalExpr(stmt.where, row))
        );
        return new QueryResult(Collections.emptyList(), Collections.emptyList(), deleted + " row(s) deleted");
    }

    // ---- UPDATE ----
    private QueryResult executeUpdate(AstNode.UpdateStatement stmt) {
        Table table = db.getTable(stmt.tableName);
        int updated = table.updateRows(
            row -> stmt.where == null || isTruthy(evalExpr(stmt.where, row)),
            row -> {
                for (AstNode.Assignment a : stmt.assignments)
                    row.put(a.column, evalExpr(a.value, row));
            }
        );
        return new QueryResult(Collections.emptyList(), Collections.emptyList(), updated + " row(s) updated");
    }

    // ---- Expression evaluator — Java 17: instanceof with cast, no pattern matching needed ----
    private Object evalExpr(AstNode node, Map<String, Object> row) {
        if (node instanceof AstNode.Literal)
            return ((AstNode.Literal) node).literalValue;

        if (node instanceof AstNode.ColumnRef) {
            AstNode.ColumnRef cr = (AstNode.ColumnRef) node;
            Object val = row.get(cr.column);
            if (val == null) val = row.get(cr.column.toLowerCase());
            return val;
        }

        if (node instanceof AstNode.FunctionCall)
            return evalFunction((AstNode.FunctionCall) node, row);

        if (node instanceof AstNode.BinaryExpr)
            return evalBinary((AstNode.BinaryExpr) node, row);

        if (node.type == AstNode.NodeType.UNARY_EXPR && "NOT".equals(node.value))
            return !isTruthy(evalExpr(node.children.get(0), row));

        if (node.type == AstNode.NodeType.ALIAS)
            return evalExpr(((AstNode.Alias) node).inner, row);

        return null;
    }

    private Object evalFunction(AstNode.FunctionCall fn, Map<String, Object> row) {
        if ("COUNT".equals(fn.funcName))  return 1L;
        if ("UPPER".equals(fn.funcName))  return fn.args.isEmpty() ? null : String.valueOf(evalExpr(fn.args.get(0), row)).toUpperCase();
        if ("LOWER".equals(fn.funcName))  return fn.args.isEmpty() ? null : String.valueOf(evalExpr(fn.args.get(0), row)).toLowerCase();
        if ("LENGTH".equals(fn.funcName)) return fn.args.isEmpty() ? null : (long) String.valueOf(evalExpr(fn.args.get(0), row)).length();
        if ("ABS".equals(fn.funcName))    return fn.args.isEmpty() ? null : Math.abs(toDouble(evalExpr(fn.args.get(0), row)));
        if ("ROUND".equals(fn.funcName))  return fn.args.isEmpty() ? null : (double) Math.round(toDouble(evalExpr(fn.args.get(0), row)));
        return null;
    }

    private Object evalBinary(AstNode.BinaryExpr be, Map<String, Object> row) {
        Object l = evalExpr(be.left, row);
        String op = be.operator;

        if ("AND".equals(op))  return isTruthy(l) && isTruthy(evalExpr(be.right, row));
        if ("OR".equals(op))   return isTruthy(l) || isTruthy(evalExpr(be.right, row));
        if ("=".equals(op))    return objectsEqual(l, evalExpr(be.right, row));
        if ("!=".equals(op) || "<>".equals(op)) return !objectsEqual(l, evalExpr(be.right, row));
        if ("<".equals(op))    return compare(l, evalExpr(be.right, row)) < 0;
        if (">".equals(op))    return compare(l, evalExpr(be.right, row)) > 0;
        if ("<=".equals(op))   return compare(l, evalExpr(be.right, row)) <= 0;
        if (">=".equals(op))   return compare(l, evalExpr(be.right, row)) >= 0;
        if ("+".equals(op))    return toDouble(l) + toDouble(evalExpr(be.right, row));
        if ("-".equals(op))    return toDouble(l) - toDouble(evalExpr(be.right, row));
        if ("*".equals(op))    return toDouble(l) * toDouble(evalExpr(be.right, row));
        if ("/".equals(op)) {
            double d = toDouble(evalExpr(be.right, row));
            return d == 0 ? null : toDouble(l) / d;
        }
        if ("LIKE".equals(op)) return likeMatch(String.valueOf(l), String.valueOf(evalExpr(be.right, row)));
        if ("IS".equals(op)) {
            Object r = evalExpr(be.right, row);
            return r == null ? l == null : objectsEqual(l, r);
        }
        if ("IN".equals(op)) {
            return be.right.children.stream().anyMatch(c -> objectsEqual(l, evalExpr(c, row)));
        }
        return null;
    }

    private boolean likeMatch(String val, String pattern) {
        String regex = "^" + pattern.replace("%", ".*").replace("_", ".") + "$";
        return val.matches(regex);
    }

    private boolean objectsEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a instanceof Number && b instanceof Number)
            return toDouble(a) == toDouble(b);
        return a.toString().equalsIgnoreCase(b.toString());
    }

    private int compare(Object a, Object b) {
        if (a instanceof Number && b instanceof Number)
            return Double.compare(toDouble(a), toDouble(b));
        return String.valueOf(a).compareToIgnoreCase(String.valueOf(b));
    }

    @SuppressWarnings("unchecked")
    private Comparable<Object> toComparable(Object val) {
        if (val instanceof Number) return (Comparable<Object>) (Comparable<?>) toDouble(val);
        return val == null ? null : (Comparable<Object>) (Comparable<?>) val.toString();
    }

    private double toDouble(Object val) {
        if (val == null)           return 0;
        if (val instanceof Number) return ((Number) val).doubleValue();
        try { return Double.parseDouble(val.toString()); }
        catch (NumberFormatException e) { return 0; }
    }

    private boolean isTruthy(Object val) {
        if (val == null)            return false;
        if (val instanceof Boolean) return (Boolean) val;
        if (val instanceof Number)  return ((Number) val).doubleValue() != 0;
        return !val.toString().isEmpty();
    }

    private String resolveTableName(AstNode from) {
        if (from == null) throw new ExecutionException("No FROM clause");
        if (from.type == AstNode.NodeType.ALIAS) return resolveTableName(from.children.get(0));
        return from.value;
    }

    public static class ExecutionException extends RuntimeException {
        public ExecutionException(String msg) { super(msg); }
    }
}
