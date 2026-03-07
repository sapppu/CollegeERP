package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hostel")
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String enrollmentNo;
    private String department;
    private String year;
    private String roomNumber;
    private String roomType;       // Single, Double, Triple
    private String hostelBlock;    // Block A, Block B, Block C, Girls Hostel
    private String admissionDate;
    private String vacatingDate;
    private Double feeAmount;
    private String feeStatus;      // Paid, Unpaid
    private String status;         // Active, Vacated

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEnrollmentNo() { return enrollmentNo; }
    public void setEnrollmentNo(String enrollmentNo) { this.enrollmentNo = enrollmentNo; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public String getHostelBlock() { return hostelBlock; }
    public void setHostelBlock(String hostelBlock) { this.hostelBlock = hostelBlock; }

    public String getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(String admissionDate) { this.admissionDate = admissionDate; }

    public String getVacatingDate() { return vacatingDate; }
    public void setVacatingDate(String vacatingDate) { this.vacatingDate = vacatingDate; }

    public Double getFeeAmount() { return feeAmount; }
    public void setFeeAmount(Double feeAmount) { this.feeAmount = feeAmount; }

    public String getFeeStatus() { return feeStatus; }
    public void setFeeStatus(String feeStatus) { this.feeStatus = feeStatus; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}