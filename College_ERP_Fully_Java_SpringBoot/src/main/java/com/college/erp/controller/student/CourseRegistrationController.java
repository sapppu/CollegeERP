package com.college.erp.controller.student;

import com.college.erp.model.Course;
import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseRegistrationController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public CourseRegistrationController(StudentRepository studentRepo,
                                        CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo  = courseRepo;
    }

    @GetMapping("/student/courseregistration")
    public String viewCourses(Authentication auth, Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        if (student != null) {
            // Courses matching student's department
            List<Course> deptCourses = courseRepo.findByDepartment(student.getDepartment());
            model.addAttribute("deptCourses", deptCourses);

            // All active courses (electives from other depts)
            List<Course> allCourses = courseRepo.findByStatus("Active");
            allCourses.removeAll(deptCourses);
            model.addAttribute("electiveCourses", allCourses);

            model.addAttribute("totalDept",     deptCourses.size());
            model.addAttribute("totalElective",  allCourses.size());
        }

        return "student/course-registration";
    }
}