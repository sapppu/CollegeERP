-- Run this script to recreate the table with new columns
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    employee_name   VARCHAR(100) NOT NULL,
    employee_address VARCHAR(255),
    employee_dob    DATE,
    employee_salary DOUBLE
);
