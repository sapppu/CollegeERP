package com.college.erp.service;

import com.college.erp.model.Expense;
import com.college.erp.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public void save(Expense expense) {
        repo.save(expense);
    }

    public List<Expense> getAll() {
        return repo.findAll();
    }

    public Expense getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void approve(Long id) {
        Expense e = repo.findById(id).get();
        e.setStatus("Approved");
        repo.save(e);
    }

    public void reject(Long id) {
        Expense e = repo.findById(id).get();
        e.setStatus("Rejected");
        repo.save(e);
    }

    public long countApproved() {
        return repo.findByStatus("Approved").size();
    }

    public long countPending() {
        return repo.findByStatus("Pending").size();
    }

    public long countRejected() {
        return repo.findByStatus("Rejected").size();
    }

    public Double getTotalApproved() {
        Double v = repo.getTotalApprovedAmount();
        return v != null ? v : 0.0;
    }

    public Double getTotalPending() {
        Double v = repo.getTotalPendingAmount();
        return v != null ? v : 0.0;
    }
}