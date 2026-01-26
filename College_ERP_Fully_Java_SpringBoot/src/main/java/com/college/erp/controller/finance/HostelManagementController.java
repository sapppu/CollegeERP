
package com.college.erp.controller.finance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance/hostelmanagement")
public class HostelManagementController {
    @GetMapping
    public String page() {
        return "HostelManagementController working";
    }
}
