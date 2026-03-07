package com.college.erp.controller.finance;

import com.college.erp.model.Transport;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.TransportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransportManagementController {

    private final TransportService transportService;
    private final StudentRepository studentRepo;
    private final DepartmentRepository deptRepo;

    public TransportManagementController(TransportService transportService,
                                         StudentRepository studentRepo,
                                         DepartmentRepository deptRepo) {
        this.transportService = transportService;
        this.studentRepo = studentRepo;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/transportmanagement")
    public String viewTransport(Model model) {
        model.addAttribute("list", transportService.getAll());
        model.addAttribute("totalRecords", transportService.countTotal());
        model.addAttribute("activeCount", transportService.countActive());
        model.addAttribute("feeUnpaidCount", transportService.countFeeUnpaid());
        model.addAttribute("totalFeeCollected", transportService.getTotalFeeCollected());
        return "admin/transport-management";
    }

    @GetMapping("/admin/add-transport")
    public String addTransportPage(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-transport";
    }

    @PostMapping("/admin/save-transport")
    public String saveTransport(Transport transport) {
        transportService.save(transport);
        return "redirect:/admin/transportmanagement";
    }

    @GetMapping("/admin/edit-transport/{id}")
    public String editTransportPage(@PathVariable Long id, Model model) {
        model.addAttribute("transport", transportService.getById(id));
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-transport";
    }

    @PostMapping("/admin/update-transport")
    public String updateTransport(Transport transport) {
        transportService.save(transport);
        return "redirect:/admin/transportmanagement";
    }

    @GetMapping("/admin/delete-transport/{id}")
    public String deleteTransport(@PathVariable Long id) {
        transportService.delete(id);
        return "redirect:/admin/transportmanagement";
    }

    @GetMapping("/admin/transport-fee-paid/{id}")
    public String markFeePaid(@PathVariable Long id) {
        transportService.markFeePaid(id);
        return "redirect:/admin/transportmanagement";
    }

    @GetMapping("/admin/transport-fee-unpaid/{id}")
    public String markFeeUnpaid(@PathVariable Long id) {
        transportService.markFeeUnpaid(id);
        return "redirect:/admin/transportmanagement";
    }
}