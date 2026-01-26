
package com.college.erp.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/departmentmanagement")
public class DepartmentManagementController {
    @GetMapping
    public String page() {
        return "DepartmentManagementController working";
    }
}
