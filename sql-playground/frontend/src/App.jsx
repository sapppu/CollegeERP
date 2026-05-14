import { useState, useEffect, useRef, useCallback } from "react";

const API = "http://localhost:8081/api";

const SAMPLE_QUERIES = [
  { label: "Select all employees", sql: "SELECT * FROM employees" },
  { label: "Filter by department", sql: "SELECT name, salary FROM employees WHERE department = 'Engineering'" },
  { label: "Order by salary desc", sql: "SELECT name, department, salary FROM employees ORDER BY salary DESC" },
  { label: "Count per department", sql: "SELECT department, COUNT(*) FROM employees GROUP BY department" },
  { label: "Salary > 80k and active", sql: "SELECT name, salary FROM employees WHERE salary > 80000 AND active = true" },
  { label: "All products", sql: "SELECT * FROM products ORDER BY price DESC" },
  { label: "Electronics only", sql: "SELECT name, price, stock FROM products WHERE category = 'Electronics'" },
  { label: "Limited results", sql: "SELECT name, salary FROM employees ORDER BY salary DESC LIMIT 3" },
  { label: "Create new table", sql: "CREATE TABLE students (\n  id INTEGER PRIMARY KEY,\n  name VARCHAR NOT NULL,\n  gpa DOUBLE\n)" },
  { label: "Insert a row", sql: "INSERT INTO employees (name, department, salary, active)\nVALUES ('Zara', 'Engineering', 92000, true)" },
  { label: "Update salary", sql: "UPDATE employees SET salary = 99000 WHERE name = 'Alice'" },
  { label: "Delete inactive", sql: "DELETE FROM employees WHERE active = false" },
];

const OP_COLORS = {
  SEQ_SCAN: "#6366f1", FILTER: "#f59e0b", SORT: "#10b981",
  LIMIT: "#ec4899", PROJECT: "#3b82f6", HASH_AGG: "#8b5cf6",
  INSERT: "#10b981", CREATE: "#10b981", DROP: "#ef4444",
  DELETE: "#ef4444", UPDATE: "#f59e0b", UNKNOWN: "#6b7280",
};

const TOKEN_COLORS = {
  SELECT: "#c084fc", FROM: "#c084fc", WHERE: "#c084fc", AND: "#c084fc",
  OR: "#c084fc", NOT: "#c084fc", INSERT: "#c084fc", INTO: "#c084fc",
  VALUES: "#c084fc", CREATE: "#c084fc", TABLE: "#c084fc", DROP: "#c084fc",
  DELETE: "#c084fc", UPDATE: "#c084fc", SET: "#c084fc", ORDER: "#c084fc",
  BY: "#c084fc", ASC: "#c084fc", DESC: "#c084fc", LIMIT: "#c084fc",
  JOIN: "#c084fc", GROUP: "#c084fc", DISTINCT: "#c084fc", AS: "#c084fc",
  IDENTIFIER: "#67e8f9", STRING_LITERAL: "#86efac", NUMBER_LITERAL: "#fcd34d",
  STAR: "#f9a8d4", EQUALS: "#f9a8d4", LT: "#f9a8d4", GT: "#f9a8d4",
  LTE: "#f9a8d4", GTE: "#f9a8d4", NOT_EQUALS: "#f9a8d4",
  COMMA: "#94a3b8", LPAREN: "#94a3b8", RPAREN: "#94a3b8", SEMICOLON: "#94a3b8",
  TRUE: "#fcd34d", FALSE: "#fcd34d", NULL: "#fcd34d",
};

function PlanTree({ node, depth = 0 }) {
  const [open, setOpen] = useState(true);
  if (!node) return null;
  const color = OP_COLORS[node.operation] || "#6b7280";
  const hasChildren = node.children && node.children.length > 0;

  return (
    <div style={{ marginLeft: depth * 20, marginBottom: 6 }}>
      <div
        onClick={() => hasChildren && setOpen(o => !o)}
        style={{
          display: "flex", alignItems: "flex-start", gap: 10,
          cursor: hasChildren ? "pointer" : "default",
          padding: "8px 12px",
          background: "rgba(255,255,255,0.04)",
          borderRadius: 8,
          borderLeft: `3px solid ${color}`,
          transition: "background .15s",
        }}
        onMouseEnter={e => e.currentTarget.style.background = "rgba(255,255,255,0.08)"}
        onMouseLeave={e => e.currentTarget.style.background = "rgba(255,255,255,0.04)"}
      >
        <div style={{ minWidth: 110 }}>
          <span style={{
            background: color + "22", color, fontSize: 11, fontWeight: 700,
            padding: "2px 8px", borderRadius: 4, letterSpacing: 1,
            fontFamily: "'JetBrains Mono', monospace"
          }}>{node.operation}</span>
        </div>
        <div style={{ flex: 1 }}>
          <div style={{ fontSize: 13, color: "#e2e8f0", marginBottom: 3 }}>{node.description}</div>
          <div style={{ display: "flex", flexWrap: "wrap", gap: 6 }}>
            {Object.entries(node.stats || {}).map(([k, v]) => (
              <span key={k} style={{ fontSize: 11, color: "#94a3b8" }}>
                <span style={{ color: "#64748b" }}>{k}:</span>{" "}
                <span style={{ color: "#cbd5e1" }}>{Array.isArray(v) ? v.join(", ") : String(v)}</span>
              </span>
            ))}
          </div>
        </div>
        {hasChildren && (
          <span style={{ color: "#475569", fontSize: 12, marginLeft: 4 }}>{open ? "▲" : "▼"}</span>
        )}
      </div>
      {open && hasChildren && node.children.map((child, i) => (
        <PlanTree key={i} node={child} depth={depth + 1} />
      ))}
    </div>
  );
}

function TokenBar({ tokens }) {
  if (!tokens || tokens.length === 0) return null;
  return (
    <div style={{ display: "flex", flexWrap: "wrap", gap: 4, padding: "10px 0" }}>
      {tokens.map((tok, i) => (
        <span key={i} style={{
          background: "rgba(255,255,255,0.06)",
          borderRadius: 4, padding: "2px 6px",
          fontSize: 11, fontFamily: "'JetBrains Mono', monospace",
          color: TOKEN_COLORS[tok.type] || "#94a3b8",
          border: `1px solid rgba(255,255,255,0.06)`,
          title: tok.type,
        }}>{tok.value || tok.type}</span>
      ))}
    </div>
  );
}

function ResultGrid({ columns, rows }) {
  if (!columns || columns.length === 0) return null;
  return (
    <div style={{ overflowX: "auto", borderRadius: 8, border: "1px solid rgba(255,255,255,0.08)" }}>
      <table style={{ width: "100%", borderCollapse: "collapse", fontSize: 13, fontFamily: "'JetBrains Mono', monospace" }}>
        <thead>
          <tr style={{ background: "rgba(99,102,241,0.15)" }}>
            <th style={{ padding: "8px 12px", textAlign: "right", color: "#475569", fontSize: 11, fontWeight: 500, borderBottom: "1px solid rgba(255,255,255,0.08)", width: 40 }}>#</th>
            {columns.map(col => (
              <th key={col} style={{ padding: "8px 16px", textAlign: "left", color: "#a5b4fc", fontWeight: 600, borderBottom: "1px solid rgba(255,255,255,0.08)", whiteSpace: "nowrap" }}>
                {col}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {rows.map((row, i) => (
            <tr key={i} style={{ borderBottom: "1px solid rgba(255,255,255,0.04)" }}
              onMouseEnter={e => e.currentTarget.style.background = "rgba(255,255,255,0.03)"}
              onMouseLeave={e => e.currentTarget.style.background = "transparent"}
            >
              <td style={{ padding: "7px 12px", textAlign: "right", color: "#334155", fontSize: 11 }}>{i + 1}</td>
              {columns.map(col => {
                const val = row[col];
                let display = val === null || val === undefined ? "NULL" : String(val);
                let color = "#e2e8f0";
                if (val === null || val === undefined) color = "#475569";
                else if (typeof val === "boolean") color = val ? "#86efac" : "#fca5a5";
                else if (typeof val === "number") color = "#fcd34d";
                else if (typeof val === "string" && !isNaN(Number(val))) color = "#fcd34d";
                return (
                  <td key={col} style={{ padding: "7px 16px", color, whiteSpace: "nowrap" }}>{display}</td>
                );
              })}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

function SchemaPanel({ schema, onTableClick }) {
  const [expanded, setExpanded] = useState({});
  return (
    <div style={{ height: "100%", overflowY: "auto" }}>
      {schema.map(table => (
        <div key={table.name} style={{ marginBottom: 4 }}>
          <div
            onClick={() => setExpanded(e => ({ ...e, [table.name]: !e[table.name] }))}
            style={{
              display: "flex", alignItems: "center", gap: 8,
              padding: "7px 12px", borderRadius: 6, cursor: "pointer",
              background: expanded[table.name] ? "rgba(99,102,241,0.12)" : "transparent",
              transition: "background .15s",
            }}
            onMouseEnter={e => { if (!expanded[table.name]) e.currentTarget.style.background = "rgba(255,255,255,0.05)"; }}
            onMouseLeave={e => { if (!expanded[table.name]) e.currentTarget.style.background = "transparent"; }}
          >
            <span style={{ color: "#6366f1", fontSize: 13 }}>⬛</span>
            <span style={{ flex: 1, fontSize: 13, fontWeight: 500, color: "#e2e8f0", fontFamily: "'JetBrains Mono', monospace" }}>{table.name}</span>
            <span style={{ fontSize: 11, color: "#475569" }}>{table.rowCount}r</span>
            <span style={{ color: "#475569", fontSize: 10 }}>{expanded[table.name] ? "▲" : "▼"}</span>
          </div>
          {expanded[table.name] && (
            <div style={{ paddingLeft: 16, paddingBottom: 4 }}>
              {table.columns.map(col => (
                <div key={col.name}
                  onClick={() => onTableClick(`SELECT * FROM ${table.name}`)}
                  style={{
                    display: "flex", alignItems: "center", gap: 6,
                    padding: "4px 8px", borderRadius: 4, cursor: "pointer",
                    fontSize: 12, color: "#94a3b8",
                  }}
                  onMouseEnter={e => e.currentTarget.style.background = "rgba(255,255,255,0.04)"}
                  onMouseLeave={e => e.currentTarget.style.background = "transparent"}
                >
                  {col.primaryKey && <span style={{ color: "#f59e0b", fontSize: 10 }}>PK</span>}
                  <span style={{ fontFamily: "'JetBrains Mono', monospace", color: "#cbd5e1" }}>{col.name}</span>
                  <span style={{ marginLeft: "auto", color: "#475569", fontSize: 11 }}>{col.type}</span>
                  {col.notNull && <span style={{ color: "#6366f1", fontSize: 10 }}>NN</span>}
                </div>
              ))}
            </div>
          )}
        </div>
      ))}
    </div>
  );
}

function SqlEditor({ value, onChange, onRun, loading }) {
  const textareaRef = useRef(null);

  const handleKeyDown = e => {
    if ((e.ctrlKey || e.metaKey) && e.key === "Enter") { e.preventDefault(); onRun(); }
    if (e.key === "Tab") {
      e.preventDefault();
      const start = e.target.selectionStart, end = e.target.selectionEnd;
      const newVal = value.substring(0, start) + "  " + value.substring(end);
      onChange(newVal);
      setTimeout(() => { e.target.selectionStart = e.target.selectionEnd = start + 2; }, 0);
    }
  };

  return (
    <div style={{ position: "relative", borderRadius: 8, overflow: "hidden", border: "1px solid rgba(99,102,241,0.3)" }}>
      <div style={{
        display: "flex", alignItems: "center", justifyContent: "space-between",
        padding: "6px 12px", background: "rgba(99,102,241,0.08)",
        borderBottom: "1px solid rgba(99,102,241,0.15)"
      }}>
        <span style={{ fontSize: 11, color: "#6366f1", fontWeight: 600, letterSpacing: 1 }}>SQL EDITOR</span>
        <div style={{ display: "flex", gap: 6, alignItems: "center" }}>
          <span style={{ fontSize: 11, color: "#475569" }}>Ctrl+Enter to run</span>
          <button
            onClick={onRun}
            disabled={loading}
            style={{
              background: loading ? "#334155" : "#6366f1",
              color: "#fff", border: "none", borderRadius: 6,
              padding: "5px 16px", fontSize: 12, fontWeight: 600,
              cursor: loading ? "not-allowed" : "pointer",
              display: "flex", alignItems: "center", gap: 6,
              transition: "background .15s",
            }}
          >
            {loading ? "Running…" : "▶ Run"}
          </button>
        </div>
      </div>
      <textarea
        ref={textareaRef}
        value={value}
        onChange={e => onChange(e.target.value)}
        onKeyDown={handleKeyDown}
        spellCheck={false}
        style={{
          width: "100%", minHeight: 160, padding: "14px 16px",
          background: "#0d1117", color: "#e2e8f0",
          border: "none", outline: "none", resize: "vertical",
          fontFamily: "'JetBrains Mono', 'Fira Code', 'Courier New', monospace",
          fontSize: 14, lineHeight: 1.7,
          boxSizing: "border-box",
        }}
      />
    </div>
  );
}

export default function App() {
  const [sql, setSql] = useState("SELECT * FROM employees ORDER BY salary DESC");
  const [result, setResult] = useState(null);
  const [schema, setSchema] = useState([]);
  const [loading, setLoading] = useState(false);
  const [activeTab, setActiveTab] = useState("results");
  const [backendDown, setBackendDown] = useState(false);

  const loadSchema = useCallback(async () => {
    try {
      const r = await fetch(`${API}/schema`);
      const d = await r.json();
      setSchema(d.tables || []);
      setBackendDown(false);
    } catch { setBackendDown(true); }
  }, []);

  useEffect(() => { loadSchema(); }, [loadSchema]);

  const runQuery = async () => {
    if (!sql.trim()) return;
    setLoading(true);
    try {
      const r = await fetch(`${API}/query`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ sql: sql.trim() }),
      });
      const d = await r.json();
      setResult(d);
      if (d.success) { await loadSchema(); setActiveTab("results"); }
      else setActiveTab("results");
    } catch (e) {
      setResult({ success: false, error: "Cannot reach backend. Is it running on :8081?", columns: [], rows: [], tokens: [] });
    }
    setLoading(false);
  };

  const resetSchema = async () => {
    await fetch(`${API}/schema/reset`, { method: "POST" });
    await loadSchema();
    setResult(null);
  };

  const TABS = ["results", "plan", "tokens"];

  return (
    <div style={{
      display: "flex", flexDirection: "column", height: "100vh",
      background: "#0a0f1a", color: "#e2e8f0",
      fontFamily: "'Inter', 'Segoe UI', sans-serif",
    }}>
      {/* Header */}
      <div style={{
        display: "flex", alignItems: "center", gap: 16, padding: "0 20px",
        height: 50, borderBottom: "1px solid rgba(255,255,255,0.07)",
        background: "rgba(99,102,241,0.06)", flexShrink: 0,
      }}>
        <div style={{ display: "flex", alignItems: "center", gap: 10 }}>
          <span style={{ fontSize: 18 }}>⬡</span>
          <span style={{ fontWeight: 700, fontSize: 15, letterSpacing: 0.5, color: "#a5b4fc" }}>SQL Playground</span>
          <span style={{ fontSize: 11, color: "#334155", background: "rgba(99,102,241,0.12)", padding: "1px 8px", borderRadius: 4 }}>Custom Java Engine</span>
        </div>
        <div style={{ flex: 1 }} />
        {backendDown && (
          <span style={{ fontSize: 12, color: "#ef4444", background: "rgba(239,68,68,0.1)", padding: "3px 10px", borderRadius: 4 }}>
            ⚠ Backend offline — run: mvn spring-boot:run
          </span>
        )}
        <button onClick={resetSchema} style={{
          fontSize: 12, color: "#64748b", background: "transparent",
          border: "1px solid rgba(255,255,255,0.08)", borderRadius: 6,
          padding: "4px 12px", cursor: "pointer",
        }}>Reset data</button>
      </div>

      {/* Main layout */}
      <div style={{ display: "flex", flex: 1, overflow: "hidden" }}>

        {/* Left sidebar: schema */}
        <div style={{
          width: 230, flexShrink: 0, borderRight: "1px solid rgba(255,255,255,0.07)",
          display: "flex", flexDirection: "column", overflow: "hidden",
        }}>
          <div style={{ padding: "10px 12px 6px", borderBottom: "1px solid rgba(255,255,255,0.06)" }}>
            <span style={{ fontSize: 11, fontWeight: 600, color: "#475569", letterSpacing: 1 }}>SCHEMA</span>
          </div>
          <div style={{ flex: 1, overflowY: "auto", padding: "6px 6px" }}>
            <SchemaPanel schema={schema} onTableClick={q => { setSql(q); }} />
          </div>
        </div>

        {/* Center: editor + tabs */}
        <div style={{ flex: 1, display: "flex", flexDirection: "column", overflow: "hidden" }}>

          {/* Sample queries */}
          <div style={{
            display: "flex", gap: 6, padding: "8px 16px", overflowX: "auto",
            borderBottom: "1px solid rgba(255,255,255,0.06)", flexShrink: 0,
            alignItems: "center",
          }}>
            <span style={{ fontSize: 11, color: "#334155", whiteSpace: "nowrap", marginRight: 4 }}>Examples:</span>
            {SAMPLE_QUERIES.map(q => (
              <button key={q.label} onClick={() => setSql(q.sql)} style={{
                fontSize: 11, whiteSpace: "nowrap", color: "#94a3b8",
                background: "rgba(255,255,255,0.04)", border: "1px solid rgba(255,255,255,0.07)",
                borderRadius: 4, padding: "3px 10px", cursor: "pointer",
                transition: "all .15s",
              }}
                onMouseEnter={e => { e.currentTarget.style.color = "#a5b4fc"; e.currentTarget.style.borderColor = "rgba(99,102,241,0.4)"; }}
                onMouseLeave={e => { e.currentTarget.style.color = "#94a3b8"; e.currentTarget.style.borderColor = "rgba(255,255,255,0.07)"; }}
              >{q.label}</button>
            ))}
          </div>

          {/* Editor */}
          <div style={{ padding: 16, flexShrink: 0 }}>
            <SqlEditor value={sql} onChange={setSql} onRun={runQuery} loading={loading} />
          </div>

          {/* Tabs */}
          <div style={{
            display: "flex", gap: 0, borderBottom: "1px solid rgba(255,255,255,0.07)",
            padding: "0 16px", flexShrink: 0,
          }}>
            {TABS.map(tab => (
              <button key={tab} onClick={() => setActiveTab(tab)} style={{
                padding: "8px 18px", fontSize: 12, fontWeight: 500,
                color: activeTab === tab ? "#a5b4fc" : "#475569",
                background: "transparent", border: "none",
                borderBottom: activeTab === tab ? "2px solid #6366f1" : "2px solid transparent",
                cursor: "pointer", textTransform: "capitalize", transition: "color .15s",
              }}>{tab}</button>
            ))}
            {result && (
              <div style={{ marginLeft: "auto", display: "flex", alignItems: "center", gap: 10, paddingRight: 4 }}>
                <span style={{
                  fontSize: 11, padding: "2px 8px", borderRadius: 4,
                  background: result.success ? "rgba(16,185,129,0.12)" : "rgba(239,68,68,0.12)",
                  color: result.success ? "#86efac" : "#fca5a5",
                }}>{result.success ? "✓" : "✗"} {result.success ? result.message : "Error"}</span>
                <span style={{ fontSize: 11, color: "#334155" }}>{result.elapsedMs}ms</span>
              </div>
            )}
          </div>

          {/* Tab content */}
          <div style={{ flex: 1, overflowY: "auto", padding: "14px 16px" }}>
            {!result && (
              <div style={{ textAlign: "center", color: "#1e293b", marginTop: 60, fontSize: 14 }}>
                Run a query to see results
              </div>
            )}

            {result && activeTab === "results" && (
              <>
                {result.error && (
                  <div style={{
                    background: "rgba(239,68,68,0.08)", border: "1px solid rgba(239,68,68,0.2)",
                    borderRadius: 8, padding: "12px 16px", color: "#fca5a5",
                    fontFamily: "'JetBrains Mono', monospace", fontSize: 13,
                  }}>
                    <span style={{ color: "#ef4444", fontWeight: 600 }}>Error: </span>{result.error}
                  </div>
                )}
                {result.success && result.columns.length === 0 && (
                  <div style={{ color: "#86efac", fontSize: 13, padding: "8px 0" }}>✓ {result.message}</div>
                )}
                {result.success && result.columns.length > 0 && (
                  <ResultGrid columns={result.columns} rows={result.rows} />
                )}
              </>
            )}

            {result && activeTab === "plan" && (
              <div>
                <div style={{ fontSize: 11, color: "#475569", marginBottom: 12 }}>
                  Execution plan — generated by the custom Java query planner. Click nodes to expand/collapse.
                </div>
                {result.plan
                  ? <PlanTree node={result.plan} />
                  : <div style={{ color: "#334155", fontSize: 13 }}>No plan available for this statement.</div>
                }
              </div>
            )}

            {result && activeTab === "tokens" && (
              <div>
                <div style={{ fontSize: 11, color: "#475569", marginBottom: 8 }}>
                  Token stream from the custom Java lexer — each token type is colour-coded.
                </div>
                <TokenBar tokens={result.tokens} />
                <div style={{ display: "flex", flexWrap: "wrap", gap: 12, marginTop: 16 }}>
                  {[["Keywords", "#c084fc"], ["Identifiers", "#67e8f9"], ["Strings", "#86efac"], ["Numbers", "#fcd34d"], ["Operators", "#f9a8d4"], ["Punctuation", "#94a3b8"]].map(([label, color]) => (
                    <div key={label} style={{ display: "flex", alignItems: "center", gap: 5, fontSize: 11, color: "#64748b" }}>
                      <div style={{ width: 10, height: 10, borderRadius: 2, background: color }} />
                      {label}
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
