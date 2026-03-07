package com.college.erp.service;

import com.college.erp.model.Scholarship;
import com.college.erp.repository.ScholarshipRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScholarshipService {

    private final ScholarshipRepository repo;

    public ScholarshipService(ScholarshipRepository repo) {
        this.repo = repo;
    }

    public void save(Scholarship scholarship) {
        repo.save(scholarship);
    }

    public List<Scholarship> getAll() {
        return repo.findAll();
    }

    public Scholarship getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void award(Long id) {
        Scholarship s = repo.findById(id).get();
        s.setStatus("Awarded");
        repo.save(s);
    }

    public void reject(Long id) {
        Scholarship s = repo.findById(id).get();
        s.setStatus("Rejected");
        repo.save(s);
    }

    public long countAwarded() {
        return repo.findByStatus("Awarded").size();
    }

    public long countPending() {
        return repo.findByStatus("Pending").size();
    }

    public long countRejected() {
        return repo.findByStatus("Rejected").size();
    }

    public Double getTotalAwarded() {
        Double v = repo.getTotalAwardedAmount();
        return v != null ? v : 0.0;
    }
}