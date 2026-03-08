package com.college.erp.repository;

import com.college.erp.model.LessonPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LessonPlanRepository extends JpaRepository<LessonPlan, Long> {
    List<LessonPlan> findByFacultyUsername(String username);
    List<LessonPlan> findByFacultyUsernameAndStatus(String username, String status);
}