package com.college.erp.controller.faculty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentProgressAnalyticsController {
    @GetMapping("/faculty/studentprogressanalytics")
    public String page() { return "faculty/student-progress-analytics"; }
}