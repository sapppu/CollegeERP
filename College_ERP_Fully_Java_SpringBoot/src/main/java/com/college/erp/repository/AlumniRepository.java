package com.college.erp.repository;

import com.college.erp.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    List<Alumni> findByStatus(String status);
    List<Alumni> findByDepartment(String department);
    List<Alumni> findByPassingYear(String passingYear);
}