package com.sqlplayground.model;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Table {

    public final String name;
    public final List<Column> columns;
    private final List<Map<String, Object>> rows = new ArrayList<>();
    private long pkCounter = 1;

    public Table(String name, List<Column> columns) {
        this.name = name;
        this.columns = Collections.unmodifiableList(new ArrayList<>(columns));
    }

    public List<Map<String, Object>> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public void insertRow(Map<String, Object> row) {
        Map<String, Object> normalized = new LinkedHashMap<>();
        for (Column col : columns) {
            Object val = row.get(col.getName());
            if (val == null && col.isPrimaryKey()) val = pkCounter++;
            normalized.put(col.getName(), val);
        }
        rows.add(normalized);
    }

    public int deleteRows(Predicate<Map<String, Object>> predicate) {
        int before = rows.size();
        rows.removeIf(predicate);
        return before - rows.size();
    }

    public int updateRows(Predicate<Map<String, Object>> predicate,
                          Consumer<Map<String, Object>> updater) {
        int count = 0;
        for (Map<String, Object> row : rows) {
            if (predicate.test(row)) {
                updater.accept(row);
                count++;
            }
        }
        return count;
    }

    public static class Column {
        private final String name;
        private final String type;
        private final boolean primaryKey;
        private final boolean notNull;

        public Column(String name, String type, boolean primaryKey, boolean notNull) {
            this.name = name;
            this.type = type;
            this.primaryKey = primaryKey;
            this.notNull = notNull;
        }

        public String getName()       { return name; }
        public String getType()       { return type; }
        public boolean isPrimaryKey() { return primaryKey; }
        public boolean isNotNull()    { return notNull; }
    }
}
