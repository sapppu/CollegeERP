package com.college.erp.repository;

import com.college.erp.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // ─── FACULTY-SIDE queries (markedBy = faculty username) ─────────────────

    List<Attendance> findByMarkedBy(String markedBy);
    List<Attendance> findByMarkedByAndSubject(String markedBy, String subject);
    List<Attendance> findByMarkedByAndDate(String markedBy, String date);

    @Query("SELECT DISTINCT a.subject FROM Attendance a WHERE a.markedBy = :username")
    List<String> findDistinctSubjectsByFaculty(String username);

    @Query("SELECT DISTINCT a.date FROM Attendance a WHERE a.markedBy = :username ORDER BY a.date DESC")
    List<String> findDistinctDatesByFaculty(String username);

    // Faculty-side status count (markedBy)
    long countByMarkedByAndStatus(String markedBy, String status);

    // ─── STUDENT-SIDE queries (studentUsername) ──────────────────────────────

    // FIX: student-side status count uses studentUsername column
    long countByStudentUsernameAndStatus(String studentUsername, String status);

    List<Attendance> findByStudentUsername(String studentUsername);

    @Query("SELECT a.subject, " +
            "SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN a.status = 'Absent'  THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN a.status = 'Late'    THEN 1 ELSE 0 END), " +
            "COUNT(a) " +
            "FROM Attendance a WHERE a.studentUsername = :username GROUP BY a.subject")
    List<Object[]> findSubjectSummaryByUsername(String username);
}
