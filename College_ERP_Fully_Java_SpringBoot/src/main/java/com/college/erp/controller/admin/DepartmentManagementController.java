package com.college.erp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentManagementController {
    @GetMapping("/admin/departmentmanagement")
    public String page() { return "admin/department-management"; }
}