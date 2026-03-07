package com.college.erp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryManagementController {
    @GetMapping("/student/librarymanagement")
    public String page() { return "student/library-management"; }
}