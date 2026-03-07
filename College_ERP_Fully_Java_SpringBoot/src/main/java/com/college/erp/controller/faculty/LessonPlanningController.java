package com.college.erp.controller.faculty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LessonPlanningController {
    @GetMapping("/faculty/lessonplanning")
    public String page() { return "faculty/lesson-planning"; }
}