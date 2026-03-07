package com.college.erp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HRPayrollController {
    @GetMapping("/admin/hrpayroll")
    public String page() { return "admin/hr-payroll"; }
}