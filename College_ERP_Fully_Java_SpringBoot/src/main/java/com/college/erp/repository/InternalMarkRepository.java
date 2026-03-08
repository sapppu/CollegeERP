package com.college.erp.repository;

import com.college.erp.model.InternalMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface InternalMarkRepository extends JpaRepository<InternalMark, Long> {

    List<InternalMark> findByFacultyUsername(String username);
    List<InternalMark> findByFacultyUsernameAndSubject(String username, String subject);
    List<InternalMark> findByFacultyUsernameAndAssessmentType(String username, String type);
    List<InternalMark> findByStudentUsername(String username);

    @Query("SELECT DISTINCT m.subject FROM InternalMark m WHERE m.facultyUsername = :username")
    List<String> findDistinctSubjectsByFaculty(String username);

    @Query("SELECT AVG(m.marksObtained) FROM InternalMark m WHERE m.facultyUsername = :username")
    Double findAvgMarksByFaculty(String username);

    long countByFacultyUsername(String username);
}