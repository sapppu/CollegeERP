package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "placement_drives")
public class PlacementDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String jobRole;
    private String jobType;
    private String eligibleDepartments;
    private String minCgpa;
    private String packageOffered;
    private String driveDate;
    private String registrationDeadline;
    private String location;
    private String description;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }

    public String getEligibleDepartments() { return eligibleDepartments; }
    public void setEligibleDepartments(String eligibleDepartments) { this.eligibleDepartments = eligibleDepartments; }

    public String getMinCgpa() { return minCgpa; }
    public void setMinCgpa(String minCgpa) { this.minCgpa = minCgpa; }

    public String getPackageOffered() { return packageOffered; }
    public void setPackageOffered(String packageOffered) { this.packageOffered = packageOffered; }

    public String getDriveDate() { return driveDate; }
    public void setDriveDate(String driveDate) { this.driveDate = driveDate; }

    public String getRegistrationDeadline() { return registrationDeadline; }
    public void setRegistrationDeadline(String registrationDeadline) { this.registrationDeadline = registrationDeadline; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}