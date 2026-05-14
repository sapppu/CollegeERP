# SQL Playground — Custom Java Query Engine

A full-stack SQL playground backed by a hand-written Java SQL engine.
Zero external databases, no Docker, no cloud dependencies.

## Architecture

```
Browser (React + Vite :3000)
    │
    ├── POST /api/query   → Lexer → Parser → Planner → Executor
    ├── GET  /api/schema  → In-memory table catalog
    └── POST /api/schema/reset → Reseed sample data

Backend (Spring Boot :8080)
    ├── Lexer         — tokenises raw SQL
    ├── Parser        — recursive descent → AST
    ├── QueryPlanner  — cost-based execution plan tree
    ├── QueryExecutor — walks AST, evaluates against in-memory tables
    └── InMemoryDatabase — ConcurrentHashMap-backed table store
```

## Prerequisites (Ubuntu)

```bash
sudo apt update
sudo apt install openjdk-21-jdk maven nodejs npm
java -version    # should show 21
mvn -version
node -version    # should show 18+
```

## Run the backend

```bash
cd sql-playground/backend
mvn spring-boot:run
# Starts on http://localhost:8080
# Sample data (employees, departments, products) seeded automatically
```

## Run the frontend

```bash
cd sql-playground/frontend
npm install
npm run dev
# Opens on http://localhost:3000
```

## Supported SQL

| Statement        | Example |
|-----------------|---------|
| SELECT           | `SELECT name, salary FROM employees WHERE salary > 80000` |
| WHERE            | `=  !=  <  >  <=  >=  AND  OR  NOT  LIKE  IS NULL  IN (...)` |
| ORDER BY         | `ORDER BY salary DESC, name ASC` |
| LIMIT / OFFSET   | `LIMIT 5 OFFSET 10` |
| GROUP BY + agg   | `SELECT dept, COUNT(*), AVG(salary) FROM employees GROUP BY dept` |
| DISTINCT         | `SELECT DISTINCT department FROM employees` |
| Functions        | `UPPER()  LOWER()  LENGTH()  ABS()  ROUND()  COUNT()  SUM()  AVG()  MAX()  MIN()` |
| CREATE TABLE     | `CREATE TABLE t (id INTEGER PRIMARY KEY, name VARCHAR NOT NULL)` |
| INSERT           | `INSERT INTO t (name) VALUES ('Alice')` |
| UPDATE           | `UPDATE employees SET salary = 99000 WHERE name = 'Alice'` |
| DELETE           | `DELETE FROM employees WHERE active = false` |
| DROP TABLE       | `DROP TABLE students` |

## Frontend features

- **Schema browser** — expandable table/column tree with types and PK/NN indicators
- **SQL editor** — multi-line textarea with Tab indent, Ctrl+Enter to run
- **Example queries** — one-click query bar with 12 sample statements
- **Results tab** — paginated grid with type-aware cell colouring
- **Plan tab** — interactive execution plan tree (expandable nodes with cost stats)
- **Tokens tab** — colour-coded token stream from the Java lexer
- **Reset button** — restores all sample data in one click

## Sample queries to try

```sql
-- Aggregation
SELECT department, COUNT(*), AVG(salary), MAX(salary)
FROM employees
GROUP BY department

-- Multi-condition filter
SELECT name, salary
FROM employees
WHERE salary > 80000 AND active = true
ORDER BY salary DESC

-- String functions
SELECT UPPER(name), LENGTH(name) FROM employees

-- Create and populate
CREATE TABLE courses (id INTEGER PRIMARY KEY, title VARCHAR NOT NULL, credits INTEGER)
INSERT INTO courses (title, credits) VALUES ('Algorithms', 4)
SELECT * FROM courses
```
