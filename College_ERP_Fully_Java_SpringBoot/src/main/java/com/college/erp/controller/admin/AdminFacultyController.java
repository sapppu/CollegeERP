package com.college.erp.controller.admin;

import com.college.erp.model.Faculty;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.AdminFacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminFacultyController {

    private final FacultyRepository facultyRepo;
    private final AdminFacultyService facultyService;

    public AdminFacultyController(FacultyRepository facultyRepo, AdminFacultyService facultyService) {
        this.facultyRepo = facultyRepo;
        this.facultyService = facultyService;
    }

    @GetMapping("/admin/add-faculty")
    public String addFacultyPage() {
        return "admin/add-faculty";
    }

    @PostMapping("/admin/save-faculty")
    public String saveFaculty(Faculty faculty, @RequestParam String password) {
        facultyService.saveFacultyWithUser(faculty, password);
        return "redirect:/admin/faculty";
    }

    @GetMapping("/admin/faculty")
    public String viewFaculty(Model model) {
        model.addAttribute("list", facultyRepo.findAll());
        return "admin/view-faculty";
    }

    @GetMapping("/admin/edit-faculty/{id}")
    public String editFaculty(@PathVariable Long id, Model model) {
        model.addAttribute("faculty", facultyRepo.findById(id).get());
        return "admin/edit-faculty";
    }

    @PostMapping("/admin/update-faculty")
    public String updateFaculty(Faculty faculty) {
        facultyService.updateFaculty(faculty);
        return "redirect:/admin/faculty";
    }

    @GetMapping("/admin/delete-faculty/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/admin/faculty";
    }
}