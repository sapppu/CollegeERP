package com.college.erp.service;

import com.college.erp.model.InternalMark;
import com.college.erp.repository.InternalMarkRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InternalMarkService {

    private final InternalMarkRepository repo;

    public InternalMarkService(InternalMarkRepository repo) { this.repo = repo; }

    public void save(InternalMark m)           { repo.save(m); }
    public void saveAll(List<InternalMark> list) { repo.saveAll(list); }
    public InternalMark getById(Long id)       { return repo.findById(id).get(); }
    public void delete(Long id)                { repo.deleteById(id); }

    public List<InternalMark> getByFaculty(String username) {
        return repo.findByFacultyUsername(username);
    }
    public List<InternalMark> getByFacultyAndSubject(String username, String subject) {
        return repo.findByFacultyUsernameAndSubject(username, subject);
    }
    public List<InternalMark> getByFacultyAndType(String username, String type) {
        return repo.findByFacultyUsernameAndAssessmentType(username, type);
    }
    public List<InternalMark> getByStudent(String username) {
        return repo.findByStudentUsername(username);
    }
    public List<String> getSubjects(String username) {
        return repo.findDistinctSubjectsByFaculty(username);
    }
    public Double getAvgMarks(String username) {
        Double avg = repo.findAvgMarksByFaculty(username);
        return avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0;
    }
    public long getTotalEntries(String username) {
        return repo.countByFacultyUsername(username);
    }
}