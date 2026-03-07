package com.college.erp.repository;

import com.college.erp.model.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
    List<FeeStructure> findByDepartment(String department);
    List<FeeStructure> findByStatus(String status);

    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FeeStructure f")
    Double getTotalFeeAmount();
}