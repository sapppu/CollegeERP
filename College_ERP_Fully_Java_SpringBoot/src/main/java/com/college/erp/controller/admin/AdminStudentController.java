package com.college.erp.controller.admin;

import com.college.erp.model.Student;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AdminStudentService;
import com.college.erp.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminStudentController {

    private final StudentRepository    studentRepo;
    private final AdminStudentService  studentService;
    private final DepartmentRepository deptRepo;
    private final ExcelService         excelService;

    public AdminStudentController(StudentRepository    studentRepo,
                                  AdminStudentService  studentService,
                                  DepartmentRepository deptRepo,
                                  ExcelService         excelService) {
        this.studentRepo    = studentRepo;
        this.studentService = studentService;
        this.deptRepo       = deptRepo;
        this.excelService   = excelService;
    }

    @GetMapping("/admin/add-student")
    public String addStudentPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-student";
    }

    @PostMapping("/admin/save-student")
    public String saveStudent(Student student, @RequestParam String password) {
        studentService.saveStudentWithUser(student, password);
        return "redirect:/admin/students";
    }

    @PostMapping("/admin/import-students")
    public String importStudents(@RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("importError", "Please select an Excel file to upload.");
            return "redirect:/admin/add-student";
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
            redirectAttributes.addFlashAttribute("importError", "Only .xlsx and .xls files are supported.");
            return "redirect:/admin/add-student";
        }

        AdminStudentService.ImportResult result = studentService.importStudentsFromExcel(file);

        redirectAttributes.addFlashAttribute("importSuccess", result.getSuccessCount());
        redirectAttributes.addFlashAttribute("importFailed", result.getFailedCount());
        redirectAttributes.addFlashAttribute("importTotal", result.getTotalRows());

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("importErrors", result.getErrors());
        }

        return "redirect:/admin/add-student";
    }

    @GetMapping("/admin/students")
    public String viewStudents(Model model) {
        model.addAttribute("list", studentRepo.findAll());
        return "admin/students";
    }

    @GetMapping("/admin/edit-student/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student",
                studentRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + id)));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-student";
    }

    @PostMapping("/admin/update-student")
    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/admin/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    @PostMapping("/admin/delete-students-bulk")
    public String deleteStudentsBulk(@RequestParam("ids") List<Long> ids,
                                     RedirectAttributes redirectAttributes) {
        int deleted = 0;
        for (Long id : ids) {
            try {
                studentService.deleteStudent(id);
                deleted++;
            } catch (Exception ignored) {}
        }
        redirectAttributes.addFlashAttribute("bulkDeleteMsg",
                deleted + " student(s) deleted successfully.");
        return "redirect:/admin/students";
    }

    @GetMapping("/admin/export-students")
    public void exportStudents(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Enrollment No", "Name", "Email", "Phone", "Gender", "DOB", "Address", "Department", "Year", "Username"};
        List<Object[]> rows = new ArrayList<>();
        for (Student s : studentRepo.findAll()) {
            rows.add(new Object[]{s.getId(), s.getEnrollmentNo(), s.getName(), s.getEmail(), s.getPhone(), s.getGender(), s.getDob(), s.getAddress(), s.getDepartment(), s.getYear(), s.getUsername()});
        }
        excelService.exportToExcel(response, "students", "Students", headers, rows);
    }
}