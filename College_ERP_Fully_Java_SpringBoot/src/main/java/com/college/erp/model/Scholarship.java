package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scholarships")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scholarshipName;
    private String studentName;
    private String enrollmentNo;
    private String department;
    private String year;
    private String scholarshipType;  // Merit, Need-Based, Sports, Government, Other
    private Double amount;
    private String academicYear;
    private String awardDate;
    private String description;
    private String status;           // Awarded, Pending, Rejected

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getScholarshipName() { return scholarshipName; }
    public void setScholarshipName(String scholarshipName) { this.scholarshipName = scholarshipName; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEnrollmentNo() { return enrollmentNo; }
    public void setEnrollmentNo(String enrollmentNo) { this.enrollmentNo = enrollmentNo; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getScholarshipType() { return scholarshipType; }
    public void setScholarshipType(String scholarshipType) { this.scholarshipType = scholarshipType; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getAwardDate() { return awardDate; }
    public void setAwardDate(String awardDate) { this.awardDate = awardDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}