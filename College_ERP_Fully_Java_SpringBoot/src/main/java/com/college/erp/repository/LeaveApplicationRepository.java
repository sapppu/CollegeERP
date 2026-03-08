package com.college.erp.repository;

import com.college.erp.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    List<LeaveApplication> findByFacultyUsername(String username);
    List<LeaveApplication> findByFacultyUsernameAndStatus(String username, String status);
    long countByFacultyUsernameAndStatus(String username, String status);
}