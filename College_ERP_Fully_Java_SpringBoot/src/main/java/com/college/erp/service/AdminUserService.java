package com.college.erp.service;

import com.college.erp.model.User;
import com.college.erp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminUserService {

    private final UserRepository repo;

    public AdminUserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllStudents() {
        return repo.findAll()
                .stream()
                .filter(u -> u.getRole().equals("ROLE_STUDENT"))
                .toList();
    }

    public List<User> getAllFaculty() {
        return repo.findAll()
                .stream()
                .filter(u -> u.getRole().equals("ROLE_FACULTY"))
                .toList();
    }
}
