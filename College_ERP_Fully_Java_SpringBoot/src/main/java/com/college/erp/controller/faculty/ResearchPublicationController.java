package com.college.erp.controller.faculty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResearchPublicationController {
    @GetMapping("/faculty/researchpublication")
    public String page() { return "faculty/research-publication"; }
}