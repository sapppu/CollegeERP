package com.employee.model;

public class Employee {
    private int id;
    private String employeeName;
    private String employeeAddress;
    private String employeeDOB; // stored as String (yyyy-MM-dd)
    private double employeeSalary;

    public Employee() {
    }

    public Employee(int id, String employeeName, String employeeAddress,
            String employeeDOB, double employeeSalary) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeDOB = employeeDOB;
        this.employeeSalary = employeeSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(String employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
