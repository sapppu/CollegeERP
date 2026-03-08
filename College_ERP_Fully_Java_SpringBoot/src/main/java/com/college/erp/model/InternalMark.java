package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "internal_marks")
public class InternalMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentUsername;
    private String studentName;
    private String department;
    private String year;
    private String subject;
    private String assessmentType;
    private Double marksObtained;
    private Double totalMarks;
    private String examDate;
    private String remarks;
    private String facultyUsername;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String s) { this.studentUsername = s; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String s) { this.studentName = s; }

    public String getDepartment() { return department; }
    public void setDepartment(String d) { this.department = d; }

    public String getYear() { return year; }
    public void setYear(String y) { this.year = y; }

    public String getSubject() { return subject; }
    public void setSubject(String s) { this.subject = s; }

    public String getAssessmentType() { return assessmentType; }
    public void setAssessmentType(String a) { this.assessmentType = a; }

    public Double getMarksObtained() { return marksObtained; }
    public void setMarksObtained(Double m) { this.marksObtained = m; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double t) { this.totalMarks = t; }

    public String getExamDate() { return examDate; }
    public void setExamDate(String e) { this.examDate = e; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String r) { this.remarks = r; }

    public String getFacultyUsername() { return facultyUsername; }
    public void setFacultyUsername(String f) { this.facultyUsername = f; }
}