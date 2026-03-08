package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examTitle;
    private String subject;
    private String examType;
    private String department;
    private String year;
    private String semester;
    private String examDate;
    private String startTime;
    private String duration;
    private String totalMarks;
    private String passingMarks;
    private String venue;
    private String instructions;
    private String status;
    private String facultyUsername;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getExamTitle() { return examTitle; }
    public void setExamTitle(String e) { this.examTitle = e; }

    public String getSubject() { return subject; }
    public void setSubject(String s) { this.subject = s; }

    public String getExamType() { return examType; }
    public void setExamType(String e) { this.examType = e; }

    public String getDepartment() { return department; }
    public void setDepartment(String d) { this.department = d; }

    public String getYear() { return year; }
    public void setYear(String y) { this.year = y; }

    public String getSemester() { return semester; }
    public void setSemester(String s) { this.semester = s; }

    public String getExamDate() { return examDate; }
    public void setExamDate(String e) { this.examDate = e; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String s) { this.startTime = s; }

    public String getDuration() { return duration; }
    public void setDuration(String d) { this.duration = d; }

    public String getTotalMarks() { return totalMarks; }
    public void setTotalMarks(String t) { this.totalMarks = t; }

    public String getPassingMarks() { return passingMarks; }
    public void setPassingMarks(String p) { this.passingMarks = p; }

    public String getVenue() { return venue; }
    public void setVenue(String v) { this.venue = v; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String i) { this.instructions = i; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    public String getFacultyUsername() { return facultyUsername; }
    public void setFacultyUsername(String f) { this.facultyUsername = f; }
}