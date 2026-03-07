package com.college.erp.controller.admin;

import com.college.erp.model.Student;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AdminStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminStudentController {

    private final StudentRepository studentRepo;
    private final AdminStudentService studentService;

    public AdminStudentController(StudentRepository studentRepo, AdminStudentService studentService) {
        this.studentRepo = studentRepo;
        this.studentService = studentService;
    }

    @GetMapping("/admin/add-student")
    public String addStudentPage() {
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
        model.addAttribute("student", studentRepo.findById(id).get());
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