-- CET Registration App - Database Setup
-- Run this script in MySQL before starting the application

CREATE DATABASE  cet_db;
USE cet_db;

CREATE TABLE  students (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    reg_no      VARCHAR(20)  UNIQUE NOT NULL,
    name        VARCHAR(100) NOT NULL,
    course      VARCHAR(50)  NOT NULL,
    dob         DATE         NOT NULL,
    password    VARCHAR(50)  NOT NULL,
    marks       INT          DEFAULT NULL
);

