package com.college.erp.service;

import com.college.erp.model.LessonPlan;
import com.college.erp.repository.LessonPlanRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonPlanService {

    private final LessonPlanRepository repo;

    public LessonPlanService(LessonPlanRepository repo) {
        this.repo = repo;
    }

    public void save(LessonPlan plan) { repo.save(plan); }

    public List<LessonPlan> getByFaculty(String username) {
        return repo.findByFacultyUsername(username);
    }

    public LessonPlan getById(Long id) { return repo.findById(id).get(); }

    public void delete(Long id) { repo.deleteById(id); }

    public long countByStatus(String username, String status) {
        return repo.findByFacultyUsernameAndStatus(username, status).size();
    }
}