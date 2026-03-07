package com.college.erp.controller.faculty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternalAssessmentController {
    @GetMapping("/faculty/internalassessment")
    public String page() { return "faculty/internal-assessment"; }
}