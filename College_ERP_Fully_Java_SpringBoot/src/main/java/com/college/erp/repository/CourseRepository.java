package com.college.erp.repository;

import com.college.erp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartment(String department);
    List<Course> findByStatus(String status);
    List<Course> findByCourseType(String courseType);
}