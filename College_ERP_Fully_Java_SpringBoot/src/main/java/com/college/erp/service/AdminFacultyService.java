package com.college.erp.service;

import com.college.erp.model.Faculty;
import com.college.erp.model.User;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminFacultyService {

    private final FacultyRepository facultyRepo;
    private final UserRepository userRepo;

    public AdminFacultyService(FacultyRepository facultyRepo, UserRepository userRepo) {
        this.facultyRepo = facultyRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void saveFacultyWithUser(Faculty faculty, String password) {
        User user = new User();
        user.setUsername(faculty.getUsername());
        user.setPassword(password);
        user.setRole("ROLE_FACULTY");
        userRepo.save(user);
        facultyRepo.save(faculty);
    }

    @Transactional
    public void updateFaculty(Faculty faculty) {
        facultyRepo.save(faculty);
    }

    @Transactional
    public void deleteFaculty(Long id) {
        Faculty faculty = facultyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found: " + id));
        userRepo.findByUsername(faculty.getUsername())
                .ifPresent(userRepo::delete);
        facultyRepo.deleteById(id);
    }
}