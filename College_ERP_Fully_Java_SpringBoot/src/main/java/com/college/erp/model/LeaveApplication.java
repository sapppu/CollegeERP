package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_applications")
public class LeaveApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facultyUsername;
    private String facultyName;
    private String department;
    private String leaveType;
    private String fromDate;
    private String toDate;
    private String totalDays;
    private String reason;
    private String status;
    private String adminRemarks;
    private String appliedOn;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFacultyUsername() { return facultyUsername; }
    public void setFacultyUsername(String f) { this.facultyUsername = f; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String f) { this.facultyName = f; }

    public String getDepartment() { return department; }
    public void setDepartment(String d) { this.department = d; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String l) { this.leaveType = l; }

    public String getFromDate() { return fromDate; }
    public void setFromDate(String f) { this.fromDate = f; }

    public String getToDate() { return toDate; }
    public void setToDate(String t) { this.toDate = t; }

    public String getTotalDays() { return totalDays; }
    public void setTotalDays(String t) { this.totalDays = t; }

    public String getReason() { return reason; }
    public void setReason(String r) { this.reason = r; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    public String getAdminRemarks() { return adminRemarks; }
    public void setAdminRemarks(String a) { this.adminRemarks = a; }

    public String getAppliedOn() { return appliedOn; }
    public void setAppliedOn(String a) { this.appliedOn = a; }
}