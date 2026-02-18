package com.college.erp.service;

import com.college.erp.model.User;
import com.college.erp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void saveUser(String username, String password, String role) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);   // plain for now
        user.setRole(role);

        repo.save(user);
    }
}
