package com.college.erp.controller.admin;

import com.college.erp.model.User;
import com.college.erp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminUserController {

    @Autowired
    private UserRepository repo;

    // open add user page
    @GetMapping("/admin/add-user")
    public String addUserPage(){
        return "admin/add-user";   // ⭐ FIXED PATH
    }

    // save user in database
    @PostMapping("/admin/save-user")
    public String saveUser(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);   // No {noop} now (we added encoder in security)
        user.setRole(role);

        repo.save(user);

        return "redirect:/admin/dashboard";
    }
}
