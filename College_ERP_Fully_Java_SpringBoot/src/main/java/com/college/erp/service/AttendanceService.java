package com.college.erp.service;

import com.college.erp.model.Attendance;
import com.college.erp.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) { this.repo = repo; }

    public void save(Attendance a)            { repo.save(a); }
    public void saveAll(List<Attendance> list) { repo.saveAll(list); }
    public Attendance getById(Long id)        { return repo.findById(id).get(); }
    public void delete(Long id)               { repo.deleteById(id); }

    public List<Attendance> getByFaculty(String username) {
        return repo.findByMarkedBy(username);
    }

    public List<Attendance> getByFacultyAndSubject(String username, String subject) {
        return repo.findByMarkedByAndSubject(username, subject);
    }

    public List<Attendance> getByFacultyAndDate(String username, String date) {
        return repo.findByMarkedByAndDate(username, date);
    }

    public List<String> getSubjects(String username) {
        return repo.findDistinctSubjectsByFaculty(username);
    }

    public List<String> getDates(String username) {
        return repo.findDistinctDatesByFaculty(username);
    }

    public long countPresent(String username) {
        return repo.countByMarkedByAndStatus(username, "Present");
    }

    public long countAbsent(String username) {
        return repo.countByMarkedByAndStatus(username, "Absent");
    }

    // Add to existing AttendanceService.java — paste these 3 methods inside the class

    public long countLate(String username) {
        return repo.countByStudentUsernameAndStatus(username, "Late");
    }

    public List<Attendance> getByUsername(String username) {
        return repo.findByStudentUsername(username);
    }

    public List<Object[]> getSubjectSummary(String username) {
        return repo.findSubjectSummaryByUsername(username);
    }
}