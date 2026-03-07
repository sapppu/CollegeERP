package com.college.erp.controller.finance;

import com.college.erp.model.Scholarship;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.ScholarshipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ScholarshipController {

    private final ScholarshipService scholarshipService;
    private final StudentRepository studentRepo;
    private final DepartmentRepository deptRepo;

    public ScholarshipController(ScholarshipService scholarshipService,
                                 StudentRepository studentRepo,
                                 DepartmentRepository deptRepo) {
        this.scholarshipService = scholarshipService;
        this.studentRepo = studentRepo;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/scholarship")
    public String viewScholarships(Model model) {
        model.addAttribute("list", scholarshipService.getAll());
        model.addAttribute("totalRecords", scholarshipService.getAll().size());
        model.addAttribute("awardedCount", scholarshipService.countAwarded());
        model.addAttribute("pendingCount", scholarshipService.countPending());
        model.addAttribute("rejectedCount", scholarshipService.countRejected());
        model.addAttribute("totalAwarded", scholarshipService.getTotalAwarded());
        return "admin/scholarship";
    }

    @GetMapping("/admin/add-scholarship")
    public String addScholarshipPage(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-scholarship";
    }

    @PostMapping("/admin/save-scholarship")
    public String saveScholarship(Scholarship scholarship) {
        scholarshipService.save(scholarship);
        return "redirect:/admin/scholarship";
    }

    @GetMapping("/admin/edit-scholarship/{id}")
    public String editScholarshipPage(@PathVariable Long id, Model model) {
        model.addAttribute("scholarship", scholarshipService.getById(id));
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-scholarship";
    }

    @PostMapping("/admin/update-scholarship")
    public String updateScholarship(Scholarship scholarship) {
        scholarshipService.save(scholarship);
        return "redirect:/admin/scholarship";
    }

    @GetMapping("/admin/delete-scholarship/{id}")
    public String deleteScholarship(@PathVariable Long id) {
        scholarshipService.delete(id);
        return "redirect:/admin/scholarship";
    }

    @GetMapping("/admin/award-scholarship/{id}")
    public String awardScholarship(@PathVariable Long id) {
        scholarshipService.award(id);
        return "redirect:/admin/scholarship";
    }

    @GetMapping("/admin/reject-scholarship/{id}")
    public String rejectScholarship(@PathVariable Long id) {
        scholarshipService.reject(id);
        return "redirect:/admin/scholarship";
    }
}