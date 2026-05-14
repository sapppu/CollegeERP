package com.sqlplayground.storage;

import com.sqlplayground.model.Table;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryDatabase {

    private final Map<String, Table> tables = new ConcurrentHashMap<>();

    public void createTable(Table table) {
        if (tables.containsKey(table.name.toLowerCase()))
            throw new IllegalStateException("Table already exists: " + table.name);
        tables.put(table.name.toLowerCase(), table);
    }

    public void dropTable(String name) {
        if (tables.remove(name.toLowerCase()) == null)
            throw new IllegalStateException("Table not found: " + name);
    }

    public Table getTable(String name) {
        Table t = tables.get(name.toLowerCase());
        if (t == null) throw new IllegalStateException("Table not found: " + name);
        return t;
    }

    public Map<String, Table> getAllTables() {
        return Collections.unmodifiableMap(tables);
    }

    public boolean tableExists(String name) {
        return tables.containsKey(name.toLowerCase());
    }

    public void seedSampleData() {
        // employees
        Table emp = new Table("employees", Arrays.asList(
            new Table.Column("id",         "INTEGER", true,  true),
            new Table.Column("name",       "VARCHAR", false, true),
            new Table.Column("department", "VARCHAR", false, false),
            new Table.Column("salary",     "DOUBLE",  false, false),
            new Table.Column("active",     "BOOLEAN", false, false)
        ));
        createTable(emp);
        insertEmployee(emp, "Alice",  "Engineering", 95000.0,  true);
        insertEmployee(emp, "Bob",    "Marketing",   72000.0,  true);
        insertEmployee(emp, "Carol",  "Engineering", 102000.0, true);
        insertEmployee(emp, "David",  "HR",          68000.0,  false);
        insertEmployee(emp, "Eve",    "Engineering", 88000.0,  true);
        insertEmployee(emp, "Frank",  "Marketing",   75000.0,  true);
        insertEmployee(emp, "Grace",  "HR",          71000.0,  true);
        insertEmployee(emp, "Henry",  "Engineering", 110000.0, false);

        // departments
        Table dept = new Table("departments", Arrays.asList(
            new Table.Column("id",       "INTEGER", true,  true),
            new Table.Column("name",     "VARCHAR", false, true),
            new Table.Column("budget",   "DOUBLE",  false, false),
            new Table.Column("location", "VARCHAR", false, false)
        ));
        createTable(dept);
        Map<String, Object> d1 = new LinkedHashMap<>();
        d1.put("id", 1L); d1.put("name", "Engineering"); d1.put("budget", 500000.0); d1.put("location", "Floor 3");
        dept.insertRow(d1);
        Map<String, Object> d2 = new LinkedHashMap<>();
        d2.put("id", 2L); d2.put("name", "Marketing"); d2.put("budget", 200000.0); d2.put("location", "Floor 1");
        dept.insertRow(d2);
        Map<String, Object> d3 = new LinkedHashMap<>();
        d3.put("id", 3L); d3.put("name", "HR"); d3.put("budget", 150000.0); d3.put("location", "Floor 2");
        dept.insertRow(d3);

        // products
        Table prod = new Table("products", Arrays.asList(
            new Table.Column("id",       "INTEGER", true,  true),
            new Table.Column("name",     "VARCHAR", false, true),
            new Table.Column("category", "VARCHAR", false, false),
            new Table.Column("price",    "DOUBLE",  false, false),
            new Table.Column("stock",    "INTEGER", false, false)
        ));
        createTable(prod);
        insertProduct(prod, 1L, "Laptop",       "Electronics", 999.99,  45);
        insertProduct(prod, 2L, "Desk Chair",   "Furniture",   299.99,  120);
        insertProduct(prod, 3L, "Monitor",      "Electronics", 449.99,  60);
        insertProduct(prod, 4L, "Keyboard",     "Electronics", 89.99,   200);
        insertProduct(prod, 5L, "Standing Desk","Furniture",   599.99,  30);
    }

    private void insertEmployee(Table t, String name, String dept, double salary, boolean active) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("name", name);
        row.put("department", dept);
        row.put("salary", salary);
        row.put("active", active);
        t.insertRow(row);
    }

    private void insertProduct(Table t, long id, String name, String category, double price, int stock) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("id", id);
        row.put("name", name);
        row.put("category", category);
        row.put("price", price);
        row.put("stock", stock);
        t.insertRow(row);
    }
}
