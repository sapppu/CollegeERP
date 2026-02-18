package com.college.erp.controller.admin;

import com.college.erp.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewUsersController {

    private final AdminUserService service;

    public AdminViewUsersController(AdminUserService service) {
        this.service = service;
    }

    @GetMapping("/admin/students")
    public String viewStudents(Model model) {
        model.addAttribute("users", service.getAllStudents());
        return "admin/view-students";
    }

    @GetMapping("/admin/faculty")
    public String viewFaculty(Model model) {
        model.addAttribute("users", service.getAllFaculty());
        return "admin/view-faculty";
    }
}
