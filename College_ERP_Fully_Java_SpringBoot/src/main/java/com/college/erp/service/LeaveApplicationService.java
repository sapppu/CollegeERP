package com.college.erp.service;

import com.college.erp.model.LeaveApplication;
import com.college.erp.repository.LeaveApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveApplicationService {

    private final LeaveApplicationRepository repo;

    public LeaveApplicationService(LeaveApplicationRepository repo) {
        this.repo = repo;
    }

    public void save(LeaveApplication l)    { repo.save(l); }
    public LeaveApplication getById(Long id){ return repo.findById(id).get(); }
    public void delete(Long id)             { repo.deleteById(id); }

    public List<LeaveApplication> getByFaculty(String username) {
        return repo.findByFacultyUsername(username);
    }
    public List<LeaveApplication> getByFacultyAndStatus(String username, String status) {
        return repo.findByFacultyUsernameAndStatus(username, status);
    }
    public long countByStatus(String username, String status) {
        return repo.countByFacultyUsernameAndStatus(username, status);
    }
    public long countTotal(String username) {
        return repo.findByFacultyUsername(username).size();
    }
}