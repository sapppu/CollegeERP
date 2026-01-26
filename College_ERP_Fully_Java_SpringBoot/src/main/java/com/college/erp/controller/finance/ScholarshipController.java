
package com.college.erp.controller.finance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance/scholarship")
public class ScholarshipController {
    @GetMapping
    public String page() {
        return "ScholarshipController working";
    }
}
