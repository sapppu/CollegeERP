package com.college.erp.service;

import com.college.erp.model.Course;
import com.college.erp.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public void save(Course course) {
        repo.save(course);
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public Course getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countActive() {
        return repo.findByStatus("Active").size();
    }

    public long countTheory() {
        return repo.findByCourseType("Theory").size();
    }

    public long countPractical() {
        return repo.findByCourseType("Practical").size();
    }
}