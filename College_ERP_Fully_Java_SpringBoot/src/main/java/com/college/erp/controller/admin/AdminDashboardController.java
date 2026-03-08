package com.college.erp.controller.admin;

import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminDashboardController {

    private final StudentRepository    studentRepo;
    private final FacultyRepository    facultyRepo;
    private final DepartmentRepository deptRepo;
    private final CourseRepository     courseRepo;

    public AdminDashboardController(StudentRepository    studentRepo,
                                    FacultyRepository    facultyRepo,
                                    DepartmentRepository deptRepo,
                                    CourseRepository     courseRepo) {
        this.studentRepo = studentRepo;
        this.facultyRepo = facultyRepo;
        this.deptRepo    = deptRepo;
        this.courseRepo  = courseRepo;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        // Stat cards
        model.addAttribute("totalStudents",  studentRepo.count());
        model.addAttribute("totalFaculty",   facultyRepo.count());
        model.addAttribute("totalDepts",     deptRepo.count());
        model.addAttribute("activeCourses",  courseRepo.findByStatus("Active").size());

        // Recent students — last 5
        var allStudents = studentRepo.findAll();
        int sCut = Math.max(0, allStudents.size() - 5);
        model.addAttribute("recentStudents", allStudents.subList(sCut, allStudents.size()));

        // Recent faculty — last 5
        var allFaculty = facultyRepo.findAll();
        int fCut = Math.max(0, allFaculty.size() - 5);
        model.addAttribute("recentFaculty", allFaculty.subList(fCut, allFaculty.size()));

        return "admin/dashboard";
    }
}