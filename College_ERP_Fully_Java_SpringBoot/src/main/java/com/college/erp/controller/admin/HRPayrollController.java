package com.college.erp.controller.admin;

import com.college.erp.model.Payroll;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.PayrollService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HRPayrollController {

    private final PayrollService payrollService;
    private final FacultyRepository facultyRepo;

    public HRPayrollController(PayrollService payrollService, FacultyRepository facultyRepo) {
        this.payrollService = payrollService;
        this.facultyRepo = facultyRepo;
    }

    @GetMapping("/admin/hrpayroll")
    public String viewPayroll(Model model) {
        model.addAttribute("list", payrollService.getAll());
        model.addAttribute("totalRecords", payrollService.getAll().size());
        model.addAttribute("paidCount", payrollService.countPaid());
        model.addAttribute("unpaidCount", payrollService.countUnpaid());
        model.addAttribute("totalPaid", payrollService.getTotalPaid());
        return "admin/hr-payroll";
    }

    @GetMapping("/admin/add-payroll")
    public String addPayrollPage(Model model) {
        model.addAttribute("facultyList", facultyRepo.findAll());
        return "admin/add-payroll";
    }

    @PostMapping("/admin/save-payroll")
    public String savePayroll(Payroll payroll) {
        payrollService.save(payroll);
        return "redirect:/admin/hrpayroll";
    }

    @GetMapping("/admin/edit-payroll/{id}")
    public String editPayrollPage(@PathVariable Long id, Model model) {
        model.addAttribute("payroll", payrollService.getById(id));
        model.addAttribute("facultyList", facultyRepo.findAll());
        return "admin/edit-payroll";
    }

    @PostMapping("/admin/update-payroll")
    public String updatePayroll(Payroll payroll) {
        payrollService.save(payroll);
        return "redirect:/admin/hrpayroll";
    }

    @GetMapping("/admin/delete-payroll/{id}")
    public String deletePayroll(@PathVariable Long id) {
        payrollService.delete(id);
        return "redirect:/admin/hrpayroll";
    }

    @GetMapping("/admin/mark-paid/{id}")
    public String markPaid(@PathVariable Long id) {
        payrollService.markPaid(id);
        return "redirect:/admin/hrpayroll";
    }

    @GetMapping("/admin/mark-unpaid/{id}")
    public String markUnpaid(@PathVariable Long id) {
        payrollService.markUnpaid(id);
        return "redirect:/admin/hrpayroll";
    }
}