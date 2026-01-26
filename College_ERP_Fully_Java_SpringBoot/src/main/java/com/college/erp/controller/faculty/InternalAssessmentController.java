
package com.college.erp.controller.faculty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty/internalassessment")
public class InternalAssessmentController {
    @GetMapping
    public String page() {
        return "InternalAssessmentController working";
    }
}
