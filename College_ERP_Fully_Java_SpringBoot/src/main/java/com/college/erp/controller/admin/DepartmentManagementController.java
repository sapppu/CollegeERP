package com.college.erp.controller.admin;

import com.college.erp.model.Department;
import com.college.erp.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentManagementController {

    private final DepartmentService service;

    public DepartmentManagementController(DepartmentService service) {
        this.service = service;
    }

    // View all departments
    @GetMapping("/admin/departmentmanagement")
    public String viewDepartments(Model model) {
        model.addAttribute("list", service.getAll());
        model.addAttribute("totalDepts", service.getAll().size());
        model.addAttribute("activeDepts", service.countActive());
        return "admin/department-management";
    }

    // Open add form
    @GetMapping("/admin/add-department")
    public String addDepartmentPage() {
        return "admin/add-department";
    }

    // Save new department
    @PostMapping("/admin/save-department")
    public String saveDepartment(Department department) {
        service.save(department);
        return "redirect:/admin/departmentmanagement";
    }

    // Open edit form
    @GetMapping("/admin/edit-department/{id}")
    public String editDepartmentPage(@PathVariable Long id, Model model) {
        model.addAttribute("department", service.getById(id));
        return "admin/edit-department";
    }

    // Update department
    @PostMapping("/admin/update-department")
    public String updateDepartment(Department department) {
        service.save(department);
        return "redirect:/admin/departmentmanagement";
    }

    // Delete department
    @GetMapping("/admin/delete-department/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/departmentmanagement";
    }
}