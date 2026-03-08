package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentUsername;
    private String studentName;
    private String department;
    private String year;
    private String subject;
    private String date;
    private String status;
    private String markedBy;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String u) { this.studentUsername = u; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String n) { this.studentName = n; }

    public String getDepartment() { return department; }
    public void setDepartment(String d) { this.department = d; }

    public String getYear() { return year; }
    public void setYear(String y) { this.year = y; }

    public String getSubject() { return subject; }
    public void setSubject(String s) { this.subject = s; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMarkedBy() { return markedBy; }
    public void setMarkedBy(String m) { this.markedBy = m; }
}