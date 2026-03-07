package com.college.erp.controller.faculty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceManagementController {
    @GetMapping("/faculty/attendancemanagement")
    public String page() { return "faculty/attendance-management"; }
}