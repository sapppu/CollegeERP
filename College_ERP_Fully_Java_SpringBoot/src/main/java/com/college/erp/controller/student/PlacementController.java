package com.college.erp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlacementController {
    @GetMapping("/student/placement")
    public String page() { return "student/placement"; }
}