package com.college.erp.service;

import com.college.erp.model.Attendance;
import com.college.erp.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) { this.repo = repo; }

    public void save(Attendance a)             { repo.save(a); }
    public void saveAll(List<Attendance> list) { repo.saveAll(list); }
    public Attendance getById(Long id)         { return repo.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found: " + id)); }
    public void delete(Long id)                { repo.deleteById(id); }

    // ─── FACULTY-SIDE methods (use markedBy) ────────────────────────────────

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

    // Faculty dashboard stat counts (counts by markedBy = faculty username)
    public long countFacultyPresent(String facultyUsername) {
        return repo.countByMarkedByAndStatus(facultyUsername, "Present");
    }

    public long countFacultyAbsent(String facultyUsername) {
        return repo.countByMarkedByAndStatus(facultyUsername, "Absent");
    }

    // ─── STUDENT-SIDE methods (use studentUsername) ──────────────────────────

    // FIX: these now correctly query by studentUsername, not markedBy
    public long countPresent(String studentUsername) {
        return repo.countByStudentUsernameAndStatus(studentUsername, "Present");
    }

    public long countAbsent(String studentUsername) {
        return repo.countByStudentUsernameAndStatus(studentUsername, "Absent");
    }

    public long countLate(String studentUsername) {
        return repo.countByStudentUsernameAndStatus(studentUsername, "Late");
    }

    public List<Attendance> getByUsername(String studentUsername) {
        return repo.findByStudentUsername(studentUsername);
    }

    public List<Object[]> getSubjectSummary(String studentUsername) {
        return repo.findSubjectSummaryByUsername(studentUsername);
    }
}
