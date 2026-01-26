
package com.college.erp.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/userrolepermission")
public class UserRolePermissionController {
    @GetMapping
    public String page() {
        return "UserRolePermissionController working";
    }
}
