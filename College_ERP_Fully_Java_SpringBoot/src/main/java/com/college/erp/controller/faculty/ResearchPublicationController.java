
package com.college.erp.controller.faculty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty/researchpublication")
public class ResearchPublicationController {
    @GetMapping
    public String page() {
        return "ResearchPublicationController working";
    }
}
