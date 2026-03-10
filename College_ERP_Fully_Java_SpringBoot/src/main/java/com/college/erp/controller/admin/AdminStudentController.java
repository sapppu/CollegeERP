package com.college.erp.controller.admin;

import com.college.erp.model.Student;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AdminStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminStudentController {

    private final StudentRepository    studentRepo;
    private final AdminStudentService  studentService;
    private final DepartmentRepository deptRepo;

    public AdminStudentController(StudentRepository    studentRepo,
                                  AdminStudentService  studentService,
                                  DepartmentRepository deptRepo) {
        this.studentRepo    = studentRepo;
        this.studentService = studentService;
        this.deptRepo       = deptRepo;
    }

    @GetMapping("/admin/add-student")
    public String addStudentPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll()); // ✅ FIX #6
        return "admin/add-student";
    }

    @PostMapping("/admin/save-student")
    public String saveStudent(Student student, @RequestParam String password) {
        studentService.saveStudentWithUser(student, password);
        return "redirect:/admin/students";
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
}