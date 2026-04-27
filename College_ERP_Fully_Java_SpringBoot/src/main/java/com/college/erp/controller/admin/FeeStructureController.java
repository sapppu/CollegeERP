package com.college.erp.controller.admin;

import com.college.erp.model.FeeStructure;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.FeeStructureService;
import com.college.erp.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FeeStructureController {

    private final FeeStructureService service;
    private final DepartmentRepository deptRepo;
    private final ExcelService excelService;

    public FeeStructureController(FeeStructureService service,
                                  DepartmentRepository deptRepo,
                                  ExcelService excelService) {
        this.service = service;
        this.deptRepo = deptRepo;
        this.excelService = excelService;
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

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/admin/export-fees")
    public void exportFees(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Department", "Year", "Semester", "Fee Category", "Amount", "Due Date", "Academic Year", "Description", "Status"};
        List<Object[]> rows = new ArrayList<>();
        for (FeeStructure f : service.getAll()) {
            rows.add(new Object[]{f.getId(), f.getDepartment(), f.getYear(), f.getSemester(), f.getFeeCategory(), f.getAmount(), f.getDueDate(), f.getAcademicYear(), f.getDescription(), f.getStatus()});
        }
        excelService.exportToExcel(response, "fee_structure", "Fee Structure", headers, rows);
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    @PostMapping("/admin/import-fees")
    public String importFees(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        if (file.isEmpty()) { ra.addFlashAttribute("importError", "Please select an Excel file."); return "redirect:/admin/add-fee"; }
        if (!excelService.isValidExcelFile(file)) { ra.addFlashAttribute("importError", "Only .xlsx and .xls files are supported."); return "redirect:/admin/add-fee"; }

        List<String> errors = new ArrayList<>();
        int successCount = 0, totalRows = 0;
        try (Workbook workbook = excelService.openWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) { ra.addFlashAttribute("importError", "Excel file is empty."); return "redirect:/admin/add-fee"; }

            String[][] aliases = {
                {"department", "dept"},
                {"year"},
                {"semester", "sem"},
                {"feecategory", "category", "feetype"},
                {"amount", "fee", "feeamount"},
                {"duedate", "deadline"},
                {"academicyear", "acadyear"},
                {"description", "desc"},
                {"status"}
            };
            int[] colMap = excelService.mapColumns(headerRow, aliases);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                totalRows++;
                try {
                    FeeStructure f = new FeeStructure();
                    f.setDepartment(excelService.getCellString(row, colMap[0]));
                    f.setYear(excelService.getCellString(row, colMap[1]));
                    f.setSemester(excelService.getCellString(row, colMap[2]));
                    f.setFeeCategory(excelService.getCellString(row, colMap[3]));
                    f.setAmount(excelService.getCellDouble(row, colMap[4]));
                    f.setDueDate(excelService.getCellString(row, colMap[5]));
                    f.setAcademicYear(excelService.getCellString(row, colMap[6]));
                    f.setDescription(excelService.getCellString(row, colMap[7]));
                    String status = excelService.getCellString(row, colMap[8]);
                    f.setStatus(status.isBlank() ? "Active" : status);

                    if (f.getDepartment() == null || f.getDepartment().isBlank()) { errors.add("Row " + (i+1) + ": Department is required."); continue; }
                    if (f.getAmount() == null) { errors.add("Row " + (i+1) + ": Amount is required."); continue; }

                    service.save(f);
                    successCount++;
                } catch (Exception e) {
                    errors.add("Row " + (i+1) + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            errors.add("Failed to read Excel file: " + e.getMessage());
        }

        ra.addFlashAttribute("importSuccess", successCount);
        ra.addFlashAttribute("importFailed", totalRows - successCount);
        ra.addFlashAttribute("importTotal", totalRows);
        if (!errors.isEmpty()) ra.addFlashAttribute("importErrors", errors);
        return "redirect:/admin/add-fee";
    }
}