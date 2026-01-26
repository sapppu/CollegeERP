package com.college.erp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDashboardController {

    @GetMapping("/student/studentdashboard")
    public String studentDashboard() {
        return "student-dashboard";
    }
}
