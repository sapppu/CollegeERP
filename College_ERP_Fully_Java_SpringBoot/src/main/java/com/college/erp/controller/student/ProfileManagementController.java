package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileManagementController {

    private final StudentRepository studentRepo;

    public ProfileManagementController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("/student/profilemanagement")
    public String viewProfile(Authentication auth, Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);
        return "student/profile-management";
    }

    @PostMapping("/student/updateprofile")
    public String updateProfile(Authentication auth,
                                @RequestParam String phone,
                                @RequestParam String address,
                                @RequestParam String email) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        if (student == null) return "redirect:/student/profilemanagement?error";
        student.setPhone(phone);
        student.setAddress(address);
        student.setEmail(email);
        studentRepo.save(student);
        studentRepo.save(student);
        return "redirect:/student/profilemanagement?success";
    }
}