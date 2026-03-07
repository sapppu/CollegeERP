package com.college.erp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcademicRecordsController {
    @GetMapping("/student/academicrecords")
    public String page() { return "student/academic-records"; }
}