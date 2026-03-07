package com.college.erp.repository;

import com.college.erp.model.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    List<Scholarship> findByStatus(String status);
    List<Scholarship> findByDepartment(String department);
    List<Scholarship> findByScholarshipType(String scholarshipType);

    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Scholarship s WHERE s.status = 'Awarded'")
    Double getTotalAwardedAmount();
}