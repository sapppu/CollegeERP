package com.sqlplayground.engine.executor;

import java.util.List;
import java.util.Map;

// Plain class instead of record — safer across Java 17 Spring Boot combinations
public class QueryResult {
    private final List<String> columns;
    private final List<Map<String, Object>> rows;
    private final String message;

    public QueryResult(List<String> columns, List<Map<String, Object>> rows, String message) {
        this.columns = columns;
        this.rows = rows;
        this.message = message;
    }

    public List<String> columns() { return columns; }
    public List<Map<String, Object>> rows()    { return rows; }
    public String message()        { return message; }
}
