package com.college.erp.controller.admin;

import com.college.erp.model.User;
import com.college.erp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/userrolepermission")
    public String viewUsers(Model model) {
        model.addAttribute("list", userService.getAll());
        model.addAttribute("totalRecords", userService.countTotal());
        model.addAttribute("adminCount",   userService.countByRole("ROLE_ADMIN"));
        model.addAttribute("facultyCount", userService.countByRole("ROLE_FACULTY"));
        model.addAttribute("studentCount", userService.countByRole("ROLE_STUDENT"));
        return "admin/user-role-permission";
    }

    @GetMapping("/admin/add-user")
    public String addUserPage() {
        return "admin/add-user";
    }

    @PostMapping("/admin/save-user")
    public String saveUser(User user, RedirectAttributes ra) {
        if (userService.usernameExists(user.getUsername())) {
            ra.addFlashAttribute("error", "Username '" + user.getUsername() + "' already exists!");
            return "redirect:/admin/add-user";
        }
        userService.save(user);
        return "redirect:/admin/userrolepermission";
    }

    @GetMapping("/admin/edit-user/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "admin/edit-user";
    }

    @PostMapping("/admin/update-user")
    public String updateUser(User user) {
        userService.save(user);
        return "redirect:/admin/userrolepermission";
    }

    @GetMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/userrolepermission";
    }
}
