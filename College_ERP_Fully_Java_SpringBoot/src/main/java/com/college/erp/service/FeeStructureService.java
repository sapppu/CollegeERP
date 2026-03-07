package com.college.erp.service;

import com.college.erp.model.FeeStructure;
import com.college.erp.repository.FeeStructureRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeeStructureService {

    private final FeeStructureRepository repo;

    public FeeStructureService(FeeStructureRepository repo) {
        this.repo = repo;
    }

    public void save(FeeStructure fee) {
        repo.save(fee);
    }

    public List<FeeStructure> getAll() {
        return repo.findAll();
    }

    public FeeStructure getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countActive() {
        return repo.findByStatus("Active").size();
    }

    public Double getTotalAmount() {
        Double total = repo.getTotalFeeAmount();
        return total != null ? total : 0.0;
    }
}