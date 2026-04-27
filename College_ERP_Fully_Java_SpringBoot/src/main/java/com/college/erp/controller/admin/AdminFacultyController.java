package com.college.erp.controller.admin;

import com.college.erp.model.Faculty;
import com.college.erp.model.User;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.UserRepository;
import com.college.erp.service.AdminFacultyService;
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
public class AdminFacultyController {

    private final FacultyRepository    facultyRepo;
    private final AdminFacultyService  facultyService;
    private final DepartmentRepository deptRepo;
    private final ExcelService         excelService;
    private final UserRepository       userRepo;

    public AdminFacultyController(FacultyRepository    facultyRepo,
                                  AdminFacultyService  facultyService,
                                  DepartmentRepository deptRepo,
                                  ExcelService         excelService,
                                  UserRepository       userRepo) {
        this.facultyRepo    = facultyRepo;
        this.facultyService = facultyService;
        this.deptRepo       = deptRepo;
        this.excelService   = excelService;
        this.userRepo       = userRepo;
    }

    @GetMapping("/admin/add-faculty")
    public String addFacultyPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
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
        model.addAttribute("faculty",
                facultyRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Faculty not found: " + id)));
        model.addAttribute("departments", deptRepo.findAll());
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

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/admin/export-faculty")
    public void exportFaculty(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Name", "Email", "Phone", "Gender", "DOB", "Address", "Department", "Designation", "Qualification", "Username"};
        List<Object[]> rows = new ArrayList<>();
        for (Faculty f : facultyRepo.findAll()) {
            rows.add(new Object[]{f.getId(), f.getName(), f.getEmail(), f.getPhone(), f.getGender(), f.getDob(), f.getAddress(), f.getDepartment(), f.getDesignation(), f.getQualification(), f.getUsername()});
        }
        excelService.exportToExcel(response, "faculty", "Faculty", headers, rows);
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    @PostMapping("/admin/import-faculty")
    public String importFaculty(@RequestParam("file") MultipartFile file,
                                RedirectAttributes ra) {
        if (file.isEmpty()) {
            ra.addFlashAttribute("importError", "Please select an Excel file to upload.");
            return "redirect:/admin/add-faculty";
        }
        if (!excelService.isValidExcelFile(file)) {
            ra.addFlashAttribute("importError", "Only .xlsx and .xls files are supported.");
            return "redirect:/admin/add-faculty";
        }

        List<String> errors = new ArrayList<>();
        int successCount = 0, totalRows = 0;

        try (Workbook workbook = excelService.openWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                ra.addFlashAttribute("importError", "Excel file is empty or has no header row.");
                return "redirect:/admin/add-faculty";
            }

            String[][] aliases = {
                {"name", "fullname", "facultyname"},
                {"email", "emailid", "emailaddress"},
                {"phone", "phoneno", "mobile"},
                {"gender", "sex"},
                {"dob", "dateofbirth"},
                {"address"},
                {"department", "dept"},
                {"designation", "position"},
                {"qualification", "degree"},
                {"username", "userid"},
                {"password", "pass"}
            };
            int[] colMap = excelService.mapColumns(headerRow, aliases);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                totalRows++;
                try {
                    Faculty f = new Faculty();
                    f.setName(excelService.getCellString(row, colMap[0]));
                    f.setEmail(excelService.getCellString(row, colMap[1]));
                    f.setPhone(excelService.getCellString(row, colMap[2]));
                    f.setGender(excelService.getCellString(row, colMap[3]));
                    f.setDob(excelService.getCellString(row, colMap[4]));
                    f.setAddress(excelService.getCellString(row, colMap[5]));
                    f.setDepartment(excelService.getCellString(row, colMap[6]));
                    f.setDesignation(excelService.getCellString(row, colMap[7]));
                    f.setQualification(excelService.getCellString(row, colMap[8]));
                    f.setUsername(excelService.getCellString(row, colMap[9]));
                    String password = excelService.getCellString(row, colMap[10]);

                    if (f.getName() == null || f.getName().isBlank()) { errors.add("Row " + (i+1) + ": Name is required."); continue; }
                    if (f.getUsername() == null || f.getUsername().isBlank()) { errors.add("Row " + (i+1) + ": Username is required."); continue; }
                    if (password == null || password.isBlank()) { errors.add("Row " + (i+1) + ": Password is required."); continue; }
                    if (userRepo.findByUsername(f.getUsername()).isPresent()) { errors.add("Row " + (i+1) + ": Username '" + f.getUsername() + "' already exists."); continue; }

                    User user = new User();
                    user.setUsername(f.getUsername());
                    user.setPassword(password);
                    user.setRole("ROLE_FACULTY");
                    userRepo.save(user);
                    facultyRepo.save(f);
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
        return "redirect:/admin/add-faculty";
    }
}