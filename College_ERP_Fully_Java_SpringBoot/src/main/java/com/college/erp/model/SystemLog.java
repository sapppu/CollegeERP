package com.college.erp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "system_logs")
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;        // LOGIN, LOGOUT, CREATE, UPDATE, DELETE, ERROR
    private String module;        // Students, Faculty, Hostel, etc.
    private String performedBy;   // username
    private String description;
    private String logLevel;      // INFO, WARNING, ERROR
    private String timestamp;

    public SystemLog() {}

    public SystemLog(String action, String module, String performedBy, String description, String logLevel) {
        this.action = action;
        this.module = module;
        this.performedBy = performedBy;
        this.description = description;
        this.logLevel = logLevel;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogLevel() { return logLevel; }
    public void setLogLevel(String logLevel) { this.logLevel = logLevel; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}