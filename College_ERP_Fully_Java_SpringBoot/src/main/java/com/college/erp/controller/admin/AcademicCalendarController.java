package com.college.erp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcademicCalendarController {
    @GetMapping("/admin/academiccalendar")
    public String page() { return "admin/academic-calendar"; }
}