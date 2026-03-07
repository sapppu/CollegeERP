package com.college.erp.service;

import com.college.erp.model.Alumni;
import com.college.erp.repository.AlumniRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlumniService {

    private final AlumniRepository repo;

    public AlumniService(AlumniRepository repo) {
        this.repo = repo;
    }

    public void save(Alumni alumni) {
        repo.save(alumni);
    }

    public List<Alumni> getAll() {
        return repo.findAll();
    }

    public Alumni getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countTotal() {
        return repo.count();
    }

    public long countActive() {
        return repo.findByStatus("Active").size();
    }
}