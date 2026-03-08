package com.college.erp.service;

import com.college.erp.model.Research;
import com.college.erp.repository.ResearchRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResearchService {

    private final ResearchRepository repo;

    public ResearchService(ResearchRepository repo) { this.repo = repo; }

    public void save(Research r)       { repo.save(r); }
    public Research getById(Long id)   { return repo.findById(id).get(); }
    public void delete(Long id)        { repo.deleteById(id); }

    public List<Research> getByFaculty(String username) {
        return repo.findByFacultyUsername(username);
    }
    public List<Research> getByFacultyAndType(String username, String type) {
        return repo.findByFacultyUsernameAndPublicationType(username, type);
    }
    public List<Research> getByFacultyAndStatus(String username, String status) {
        return repo.findByFacultyUsernameAndStatus(username, status);
    }
    public long countTotal(String username) {
        return repo.countByFacultyUsername(username);
    }
    public long countByType(String username, String type) {
        return repo.countByFacultyUsernameAndPublicationType(username, type);
    }
}