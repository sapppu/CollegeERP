package com.college.erp.service;

import com.college.erp.model.Department;
import com.college.erp.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public void save(Department department) {
        repo.save(department);
    }

    public List<Department> getAll() {
        return repo.findAll();
    }

    public Department getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countActive() {
        return repo.findByStatus("Active").size();
    }
}