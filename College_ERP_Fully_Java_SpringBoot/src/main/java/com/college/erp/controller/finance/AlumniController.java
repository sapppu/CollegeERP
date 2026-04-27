package com.college.erp.controller.finance;

import com.college.erp.model.Alumni;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.AlumniService;
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
public class AlumniController {

    private final AlumniService alumniService;
    private final DepartmentRepository deptRepo;
    private final ExcelService excelService;

    public AlumniController(AlumniService alumniService,
                            DepartmentRepository deptRepo,
                            ExcelService excelService) {
        this.alumniService = alumniService;
        this.deptRepo = deptRepo;
        this.excelService = excelService;
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

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/admin/export-alumni")
    public void exportAlumni(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Name", "Enrollment No", "Department", "Passing Year", "Degree", "Email", "Phone", "City", "Company", "Designation", "LinkedIn", "Status"};
        List<Object[]> rows = new ArrayList<>();
        for (Alumni a : alumniService.getAll()) {
            rows.add(new Object[]{a.getId(), a.getName(), a.getEnrollmentNo(), a.getDepartment(), a.getPassingYear(), a.getDegree(), a.getEmail(), a.getPhone(), a.getCurrentCity(), a.getCurrentCompany(), a.getCurrentDesignation(), a.getLinkedin(), a.getStatus()});
        }
        excelService.exportToExcel(response, "alumni", "Alumni", headers, rows);
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    @PostMapping("/admin/import-alumni")
    public String importAlumni(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        if (file.isEmpty()) { ra.addFlashAttribute("importError", "Please select an Excel file."); return "redirect:/admin/add-alumni"; }
        if (!excelService.isValidExcelFile(file)) { ra.addFlashAttribute("importError", "Only .xlsx and .xls files are supported."); return "redirect:/admin/add-alumni"; }

        List<String> errors = new ArrayList<>();
        int successCount = 0, totalRows = 0;
        try (Workbook workbook = excelService.openWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) { ra.addFlashAttribute("importError", "Excel file is empty."); return "redirect:/admin/add-alumni"; }

            String[][] aliases = {
                {"name", "fullname", "alumniname"},
                {"enrollmentno", "enrollno", "rollno"},
                {"department", "dept"},
                {"passingyear", "yearofpassing", "graduationyear"},
                {"degree", "qualification"},
                {"email", "emailid"},
                {"phone", "phoneno", "mobile"},
                {"city", "currentcity", "location"},
                {"company", "currentcompany", "organization"},
                {"designation", "currentdesignation", "position"},
                {"linkedin", "linkedinurl", "profile"},
                {"description", "desc", "notes"},
                {"status"}
            };
            int[] colMap = excelService.mapColumns(headerRow, aliases);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                totalRows++;
                try {
                    Alumni a = new Alumni();
                    a.setName(excelService.getCellString(row, colMap[0]));
                    a.setEnrollmentNo(excelService.getCellString(row, colMap[1]));
                    a.setDepartment(excelService.getCellString(row, colMap[2]));
                    a.setPassingYear(excelService.getCellString(row, colMap[3]));
                    a.setDegree(excelService.getCellString(row, colMap[4]));
                    a.setEmail(excelService.getCellString(row, colMap[5]));
                    a.setPhone(excelService.getCellString(row, colMap[6]));
                    a.setCurrentCity(excelService.getCellString(row, colMap[7]));
                    a.setCurrentCompany(excelService.getCellString(row, colMap[8]));
                    a.setCurrentDesignation(excelService.getCellString(row, colMap[9]));
                    a.setLinkedin(excelService.getCellString(row, colMap[10]));
                    a.setDescription(excelService.getCellString(row, colMap[11]));
                    String status = excelService.getCellString(row, colMap[12]);
                    a.setStatus(status.isBlank() ? "Active" : status);

                    if (a.getName() == null || a.getName().isBlank()) { errors.add("Row " + (i+1) + ": Name is required."); continue; }

                    alumniService.save(a);
                    successCount++;
                } catch (Exception ex) {
                    errors.add("Row " + (i+1) + ": " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            errors.add("Failed to read Excel file: " + e.getMessage());
        }

        ra.addFlashAttribute("importSuccess", successCount);
        ra.addFlashAttribute("importFailed", totalRows - successCount);
        ra.addFlashAttribute("importTotal", totalRows);
        if (!errors.isEmpty()) ra.addFlashAttribute("importErrors", errors);
        return "redirect:/admin/add-alumni";
    }
}