
package com.college.erp.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/coursesyllabus")
public class CourseSyllabusController {
    @GetMapping
    public String page() {
        return "CourseSyllabusController working";
    }
}
