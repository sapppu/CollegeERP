package com.college.erp.controller.faculty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExaminationManagementController {
    @GetMapping("/faculty/examinationmanagement")
    public String page() { return "faculty/examination-management"; }
}