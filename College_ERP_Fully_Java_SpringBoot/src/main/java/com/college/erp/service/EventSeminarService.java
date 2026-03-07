package com.college.erp.service;

import com.college.erp.model.EventSeminar;
import com.college.erp.repository.EventSeminarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventSeminarService {

    private final EventSeminarRepository repo;

    public EventSeminarService(EventSeminarRepository repo) {
        this.repo = repo;
    }

    public void save(EventSeminar event) {
        repo.save(event);
    }

    public List<EventSeminar> getAll() {
        return repo.findAll();
    }

    public EventSeminar getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void markCompleted(Long id) {
        EventSeminar e = repo.findById(id).get();
        e.setStatus("Completed");
        repo.save(e);
    }

    public void markCancelled(Long id) {
        EventSeminar e = repo.findById(id).get();
        e.setStatus("Cancelled");
        repo.save(e);
    }

    public long countTotal() { return repo.count(); }

    public long countUpcoming() {
        return repo.findByStatus("Upcoming").size();
    }

    public long countOngoing() {
        return repo.findByStatus("Ongoing").size();
    }

    public long countCompleted() {
        return repo.findByStatus("Completed").size();
    }
}