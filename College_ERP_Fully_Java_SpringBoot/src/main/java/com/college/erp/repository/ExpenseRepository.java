package com.college.erp.repository;

import com.college.erp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByStatus(String status);
    List<Expense> findByCategory(String category);
    List<Expense> findByDepartment(String department);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.status = 'Approved'")
    Double getTotalApprovedAmount();

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.status = 'Pending'")
    Double getTotalPendingAmount();
}