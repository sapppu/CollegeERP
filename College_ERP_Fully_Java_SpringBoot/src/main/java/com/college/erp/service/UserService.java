package com.college.erp.service;

import com.college.erp.model.User;
import com.college.erp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void save(User user) {
        repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countTotal() {
        return repo.count();
    }

    public long countByRole(String role) {
        return repo.findAll().stream()
                .filter(u -> role.equals(u.getRole()))
                .count();
    }

    public boolean usernameExists(String username) {
        return repo.findByUsername(username).isPresent();
    }
}