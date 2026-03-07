package com.college.erp.service;

import com.college.erp.model.Payroll;
import com.college.erp.repository.PayrollRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository repo;

    public PayrollService(PayrollRepository repo) {
        this.repo = repo;
    }

    public void save(Payroll payroll) {
        // auto-calculate net salary before saving
        if (payroll.getBasicSalary() != null) {
            double allowances = payroll.getAllowances() != null ? payroll.getAllowances() : 0;
            double deductions = payroll.getDeductions() != null ? payroll.getDeductions() : 0;
            payroll.setNetSalary(payroll.getBasicSalary() + allowances - deductions);
        }
        repo.save(payroll);
    }

    public List<Payroll> getAll() {
        return repo.findAll();
    }

    public Payroll getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void markPaid(Long id) {
        Payroll p = repo.findById(id).get();
        p.setStatus("Paid");
        repo.save(p);
    }

    public void markUnpaid(Long id) {
        Payroll p = repo.findById(id).get();
        p.setStatus("Unpaid");
        repo.save(p);
    }

    public long countPaid() {
        return repo.findByStatus("Paid").size();
    }

    public long countUnpaid() {
        return repo.findByStatus("Unpaid").size();
    }

    public Double getTotalPaid() {
        Double total = repo.getTotalPaidAmount();
        return total != null ? total : 0.0;
    }
}