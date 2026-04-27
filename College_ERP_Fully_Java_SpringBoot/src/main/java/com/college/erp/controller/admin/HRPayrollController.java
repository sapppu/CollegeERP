package com.college.erp.controller.admin;

import com.college.erp.model.Payroll;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.PayrollService;
import com.college.erp.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HRPayrollController {

    private final PayrollService payrollService;
    private final FacultyRepository facultyRepo;
    private final ExcelService excelService;

    public HRPayrollController(PayrollService payrollService,
                               FacultyRepository facultyRepo,
                               ExcelService excelService) {
        this.payrollService = payrollService;
        this.facultyRepo = facultyRepo;
        this.excelService = excelService;
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

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/admin/export-payroll")
    public void exportPayroll(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Faculty Name", "Department", "Designation", "Month", "Year", "Basic Salary", "Allowances", "Deductions", "Net Salary", "Status"};
        List<Object[]> rows = new ArrayList<>();
        for (Payroll p : payrollService.getAll()) {
            rows.add(new Object[]{p.getId(), p.getFacultyName(), p.getDepartment(), p.getDesignation(), p.getMonth(), p.getYear(), p.getBasicSalary(), p.getAllowances(), p.getDeductions(), p.getNetSalary(), p.getStatus()});
        }
        excelService.exportToExcel(response, "payroll", "Payroll", headers, rows);
    }
}