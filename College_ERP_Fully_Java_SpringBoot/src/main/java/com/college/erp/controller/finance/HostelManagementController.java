package com.college.erp.controller.finance;

import com.college.erp.model.Hostel;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.HostelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HostelManagementController {

    private final HostelService hostelService;
    private final StudentRepository studentRepo;
    private final DepartmentRepository deptRepo;

    public HostelManagementController(HostelService hostelService,
                                      StudentRepository studentRepo,
                                      DepartmentRepository deptRepo) {
        this.hostelService = hostelService;
        this.studentRepo = studentRepo;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/hostelmanagement")
    public String viewHostel(Model model) {
        model.addAttribute("list", hostelService.getAll());
        model.addAttribute("totalRecords", hostelService.countTotal());
        model.addAttribute("activeCount", hostelService.countActive());
        model.addAttribute("feeUnpaidCount", hostelService.countFeeUnpaid());
        model.addAttribute("totalFeeCollected", hostelService.getTotalFeeCollected());
        return "admin/hostel-management";
    }

    @GetMapping("/admin/add-hostel")
    public String addHostelPage(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-hostel";
    }

    @PostMapping("/admin/save-hostel")
    public String saveHostel(Hostel hostel) {
        hostelService.save(hostel);
        return "redirect:/admin/hostelmanagement";
    }

    @GetMapping("/admin/edit-hostel/{id}")
    public String editHostelPage(@PathVariable Long id, Model model) {
        model.addAttribute("hostel", hostelService.getById(id));
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-hostel";
    }

    @PostMapping("/admin/update-hostel")
    public String updateHostel(Hostel hostel) {
        hostelService.save(hostel);
        return "redirect:/admin/hostelmanagement";
    }

    @GetMapping("/admin/delete-hostel/{id}")
    public String deleteHostel(@PathVariable Long id) {
        hostelService.delete(id);
        return "redirect:/admin/hostelmanagement";
    }

    @GetMapping("/admin/hostel-fee-paid/{id}")
    public String markFeePaid(@PathVariable Long id) {
        hostelService.markFeePaid(id);
        return "redirect:/admin/hostelmanagement";
    }

    @GetMapping("/admin/hostel-fee-unpaid/{id}")
    public String markFeeUnpaid(@PathVariable Long id) {
        hostelService.markFeeUnpaid(id);
        return "redirect:/admin/hostelmanagement";
    }

    @GetMapping("/admin/vacate-hostel/{id}")
    public String vacateHostel(@PathVariable Long id) {
        hostelService.vacate(id);
        return "redirect:/admin/hostelmanagement";
    }
}