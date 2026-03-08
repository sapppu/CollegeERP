package com.college.erp.service;

import com.college.erp.model.Exam;
import com.college.erp.repository.ExamRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamService {

    private final ExamRepository repo;

    public ExamService(ExamRepository repo) { this.repo = repo; }

    public void save(Exam e)       { repo.save(e); }
    public Exam getById(Long id)   { return repo.findById(id).get(); }
    public void delete(Long id)    { repo.deleteById(id); }

    public List<Exam> getByFaculty(String username) {
        return repo.findByFacultyUsername(username);
    }
    public List<Exam> getByFacultyAndStatus(String username, String status) {
        return repo.findByFacultyUsernameAndStatus(username, status);
    }
    public List<Exam> getByFacultyAndType(String username, String type) {
        return repo.findByFacultyUsernameAndExamType(username, type);
    }
    public long countByStatus(String username, String status) {
        return repo.countByFacultyUsernameAndStatus(username, status);
    }
    public long countTotal(String username) {
        return repo.findByFacultyUsername(username).size();
    }
}