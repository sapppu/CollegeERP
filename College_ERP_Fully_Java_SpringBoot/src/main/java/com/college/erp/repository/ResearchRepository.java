package com.college.erp.repository;

import com.college.erp.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResearchRepository extends JpaRepository<Research, Long> {
    List<Research> findByFacultyUsername(String username);
    List<Research> findByFacultyUsernameAndPublicationType(String username, String type);
    List<Research> findByFacultyUsernameAndStatus(String username, String status);
    long countByFacultyUsernameAndPublicationType(String username, String type);
    long countByFacultyUsername(String username);
}