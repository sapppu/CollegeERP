package com.college.erp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeePaymentController {
    @GetMapping("/student/feepayment")
    public String page() { return "student/fee-payment"; }
}