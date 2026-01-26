
package com.college.erp.controller.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/courseregistration")
public class CourseRegistrationController {
    @GetMapping
    public String page() {
        return "CourseRegistrationController working";
    }
}
