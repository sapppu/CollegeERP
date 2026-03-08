package com.college.erp.repository;

import com.college.erp.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByFacultyUsername(String username);
    List<Exam> findByFacultyUsernameAndStatus(String username, String status);
    List<Exam> findByFacultyUsernameAndExamType(String username, String type);
    long countByFacultyUsernameAndStatus(String username, String status);
}