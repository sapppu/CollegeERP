package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AccountSettingsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileManagementController {

    private final StudentRepository studentRepo;
    private final AccountSettingsService accountSettings;

    public ProfileManagementController(StudentRepository studentRepo,
                                       AccountSettingsService accountSettings) {
        this.studentRepo = studentRepo;
        this.accountSettings = accountSettings;
    }

    @GetMapping("/student/profilemanagement")
    public String viewProfile(Authentication auth, Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);
        if (student != null && student.getProfilePicture() != null) {
            model.addAttribute("profilePictureUrl",
                    accountSettings.publicUrl(student.getProfilePicture()));
        }
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
        return "redirect:/student/profilemanagement?success=profile";
    }

    @PostMapping("/student/changepassword")
    public String changePassword(Authentication auth,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 RedirectAttributes ra) {
        var result = accountSettings.changePassword(
                auth.getName(), oldPassword, newPassword, confirmPassword);
        ra.addAttribute("pwd", switch (result) {
            case SUCCESS -> "success";
            case WRONG_OLD_PASSWORD -> "wrong";
            case PASSWORDS_MISMATCH -> "mismatch";
            case TOO_SHORT -> "short";
            default -> "error";
        });
        return "redirect:/student/profilemanagement";
    }

    @PostMapping("/student/upload-profile-picture")
    public String uploadPicture(Authentication auth,
                                @RequestParam("photo") MultipartFile photo,
                                RedirectAttributes ra) {
        Student student = studentRepo.findByUsername(auth.getName());
        if (student == null) {
            return "redirect:/student/profilemanagement?photo=error";
        }
        try {
            String filename = accountSettings.storeProfilePicture(
                    auth.getName(), "student", photo, student.getProfilePicture());
            student.setProfilePicture(filename);
            studentRepo.save(student);
            ra.addAttribute("photo", "success");
        } catch (IllegalArgumentException ex) {
            ra.addAttribute("photo", ex.getMessage());
        } catch (Exception ex) {
            ra.addAttribute("photo", "error");
        }
        return "redirect:/student/profilemanagement";
    }

    @PostMapping("/student/remove-profile-picture")
    public String removePicture(Authentication auth) {
        Student student = studentRepo.findByUsername(auth.getName());
        if (student != null) {
            accountSettings.deleteProfilePictureFile(student.getProfilePicture());
            student.setProfilePicture(null);
            studentRepo.save(student);
        }
        return "redirect:/student/profilemanagement?photo=removed";
    }
}
