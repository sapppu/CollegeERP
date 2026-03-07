package com.college.erp.service;

import com.college.erp.model.Student;
import com.college.erp.model.User;
import com.college.erp.repository.StudentRepository;
import com.college.erp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminStudentService {

    private final StudentRepository studentRepo;
    private final UserRepository userRepo;

    public AdminStudentService(StudentRepository studentRepo, UserRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    public void saveStudentWithUser(Student student, String password) {
        User user = new User();
        user.setUsername(student.getUsername());
        user.setPassword(password);
        user.setRole("ROLE_STUDENT");
        userRepo.save(user);
        studentRepo.save(student);
    }

    public void updateStudent(Student student) {
        studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id).get();
        userRepo.findByUsername(student.getUsername())
                .ifPresent(userRepo::delete);
        studentRepo.deleteById(id);
    }
}