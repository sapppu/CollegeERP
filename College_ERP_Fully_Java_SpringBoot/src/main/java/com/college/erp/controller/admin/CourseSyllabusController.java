package com.college.erp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseSyllabusController {
    @GetMapping("/admin/coursesyllabus")
    public String page() { return "admin/course-syllabus"; }
}