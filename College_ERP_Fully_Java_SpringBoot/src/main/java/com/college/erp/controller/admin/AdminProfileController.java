package com.college.erp.controller.admin;

import com.college.erp.repository.UserRepository;
import com.college.erp.service.AccountSettingsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminProfileController {

    private final UserRepository userRepo;
    private final AccountSettingsService accountSettings;

    public AdminProfileController(UserRepository userRepo,
                                  AccountSettingsService accountSettings) {
        this.userRepo = userRepo;
        this.accountSettings = accountSettings;
    }

    @GetMapping("/admin/profile")
    public String viewProfile(Authentication auth, Model model) {
        model.addAttribute("username", auth.getName());
        userRepo.findByUsername(auth.getName()).ifPresent(u -> model.addAttribute("role", u.getRole()));
        return "admin/profile";
    }

    @PostMapping("/admin/changepassword")
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
        return "redirect:/admin/profile";
    }
}
