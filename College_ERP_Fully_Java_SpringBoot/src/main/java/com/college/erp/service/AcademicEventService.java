package com.college.erp.service;

import com.college.erp.model.AcademicEvent;
import com.college.erp.repository.AcademicEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcademicEventService {

    private final AcademicEventRepository repo;

    public AcademicEventService(AcademicEventRepository repo) {
        this.repo = repo;
    }

    public void save(AcademicEvent event) {
        repo.save(event);
    }

    public List<AcademicEvent> getAll() {
        return repo.findAll();
    }

    public AcademicEvent getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countByType(String type) {
        return repo.findByEventType(type).size();
    }

    public long countUpcoming() {
        return repo.findByStatus("Upcoming").size();
    }
}