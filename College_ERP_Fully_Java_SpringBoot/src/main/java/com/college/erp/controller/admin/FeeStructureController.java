package com.college.erp.controller.admin;

import com.college.erp.model.FeeStructure;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.FeeStructureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeeStructureController {

    private final FeeStructureService service;
    private final DepartmentRepository deptRepo;

    public FeeStructureController(FeeStructureService service,
                                  DepartmentRepository deptRepo) {
        this.service = service;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/feestructure")
    public String viewFeeStructure(Model model) {
        model.addAttribute("list", service.getAll());
        model.addAttribute("totalFees", service.getAll().size());
        model.addAttribute("activeFees", service.countActive());
        model.addAttribute("totalAmount", service.getTotalAmount());
        return "admin/fee-structure";
    }

    @GetMapping("/admin/add-fee")
    public String addFeePage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-fee";
    }

    @PostMapping("/admin/save-fee")
    public String saveFee(FeeStructure fee) {
        service.save(fee);
        return "redirect:/admin/feestructure";
    }

    @GetMapping("/admin/edit-fee/{id}")
    public String editFeePage(@PathVariable Long id, Model model) {
        model.addAttribute("fee", service.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-fee";
    }

    @PostMapping("/admin/update-fee")
    public String updateFee(FeeStructure fee) {
        service.save(fee);
        return "redirect:/admin/feestructure";
    }

    @GetMapping("/admin/delete-fee/{id}")
    public String deleteFee(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/feestructure";
    }
}