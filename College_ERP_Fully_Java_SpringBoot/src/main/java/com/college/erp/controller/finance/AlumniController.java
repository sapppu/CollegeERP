package com.college.erp.controller.finance;

import com.college.erp.model.Alumni;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.AlumniService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlumniController {

    private final AlumniService alumniService;
    private final DepartmentRepository deptRepo;

    public AlumniController(AlumniService alumniService,
                            DepartmentRepository deptRepo) {
        this.alumniService = alumniService;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/alumni")
    public String viewAlumni(Model model) {
        model.addAttribute("list", alumniService.getAll());
        model.addAttribute("totalRecords", alumniService.countTotal());
        model.addAttribute("activeCount", alumniService.countActive());
        return "admin/alumni";
    }

    @GetMapping("/admin/add-alumni")
    public String addAlumniPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-alumni";
    }

    @PostMapping("/admin/save-alumni")
    public String saveAlumni(Alumni alumni) {
        alumniService.save(alumni);
        return "redirect:/admin/alumni";
    }

    @GetMapping("/admin/edit-alumni/{id}")
    public String editAlumniPage(@PathVariable Long id, Model model) {
        model.addAttribute("alumni", alumniService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-alumni";
    }

    @PostMapping("/admin/update-alumni")
    public String updateAlumni(Alumni alumni) {
        alumniService.save(alumni);
        return "redirect:/admin/alumni";
    }

    @GetMapping("/admin/delete-alumni/{id}")
    public String deleteAlumni(@PathVariable Long id) {
        alumniService.delete(id);
        return "redirect:/admin/alumni";
    }
}