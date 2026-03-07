package com.college.erp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperAdminDashboardController {
    @GetMapping("/admin/superadmindashboard")
    public String page() { return "admin/super-admin-dashboard"; }
}