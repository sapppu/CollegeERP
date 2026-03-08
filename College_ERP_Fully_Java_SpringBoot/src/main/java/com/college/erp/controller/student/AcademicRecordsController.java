package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcademicRecordsController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public AcademicRecordsController(StudentRepository studentRepo,
                                     CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo  = courseRepo;
    }

    @GetMapping("/student/academicrecords")
    public String viewRecords(Authentication auth, Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        if (student != null) {
            model.addAttribute("courses",
                    courseRepo.findByDepartment(student.getDepartment()));
        }

        return "student/academic-records";
    }
}