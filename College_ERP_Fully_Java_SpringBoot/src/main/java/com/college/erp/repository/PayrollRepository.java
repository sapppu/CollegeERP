package com.college.erp.repository;

import com.college.erp.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByStatus(String status);
    List<Payroll> findByDepartment(String department);
    List<Payroll> findByMonth(String month);

    @Query("SELECT COALESCE(SUM(p.netSalary), 0) FROM Payroll p WHERE p.status = 'Paid'")
    Double getTotalPaidAmount();
}