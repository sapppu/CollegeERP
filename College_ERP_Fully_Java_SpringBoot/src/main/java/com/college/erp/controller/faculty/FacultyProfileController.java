package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.AccountSettingsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FacultyProfileController {

    private final FacultyRepository facultyRepo;
    private final AccountSettingsService accountSettings;

    public FacultyProfileController(FacultyRepository facultyRepo,
                                    AccountSettingsService accountSettings) {
        this.facultyRepo = facultyRepo;
        this.accountSettings = accountSettings;
    }

    @GetMapping("/faculty/profilemanagement")
    public String viewProfile(Authentication auth, Model model) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        model.addAttribute("faculty", faculty);
        if (faculty != null && faculty.getProfilePicture() != null) {
            model.addAttribute("profilePictureUrl",
                    accountSettings.publicUrl(faculty.getProfilePicture()));
        }
        return "faculty/profile-management";
    }

    @PostMapping("/faculty/updateprofile")
    public String updateProfile(Authentication auth,
                                @RequestParam String phone,
                                @RequestParam String address,
                                @RequestParam String email) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        if (faculty == null) return "redirect:/faculty/profilemanagement?error";
        faculty.setPhone(phone);
        faculty.setAddress(address);
        faculty.setEmail(email);
        facultyRepo.save(faculty);
        return "redirect:/faculty/profilemanagement?success=profile";
    }

    @PostMapping("/faculty/changepassword")
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
        return "redirect:/faculty/profilemanagement";
    }

    @PostMapping("/faculty/upload-profile-picture")
    public String uploadPicture(Authentication auth,
                                @RequestParam("photo") MultipartFile photo,
                                RedirectAttributes ra) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        if (faculty == null) {
            return "redirect:/faculty/profilemanagement?photo=error";
        }
        try {
            String filename = accountSettings.storeProfilePicture(
                    auth.getName(), "faculty", photo, faculty.getProfilePicture());
            faculty.setProfilePicture(filename);
            facultyRepo.save(faculty);
            ra.addAttribute("photo", "success");
        } catch (IllegalArgumentException ex) {
            ra.addAttribute("photo", ex.getMessage());
        } catch (Exception ex) {
            ra.addAttribute("photo", "error");
        }
        return "redirect:/faculty/profilemanagement";
    }

    @PostMapping("/faculty/remove-profile-picture")
    public String removePicture(Authentication auth) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        if (faculty != null) {
            accountSettings.deleteProfilePictureFile(faculty.getProfilePicture());
            faculty.setProfilePicture(null);
            facultyRepo.save(faculty);
        }
        return "redirect:/faculty/profilemanagement?photo=removed";
    }
}
