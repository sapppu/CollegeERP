package com.college.erp.controller.admin;

import com.college.erp.model.Course;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseSyllabusController {

    private final CourseService courseService;
    private final DepartmentRepository deptRepo;
    private final FacultyRepository facultyRepo;

    public CourseSyllabusController(CourseService courseService,
                                    DepartmentRepository deptRepo,
                                    FacultyRepository facultyRepo) {
        this.courseService = courseService;
        this.deptRepo = deptRepo;
        this.facultyRepo = facultyRepo;
    }

    @GetMapping("/admin/coursesyllabus")
    public String viewCourses(Model model) {
        model.addAttribute("list", courseService.getAll());
        model.addAttribute("totalCourses", courseService.getAll().size());
        model.addAttribute("activeCourses", courseService.countActive());
        model.addAttribute("theoryCourses", courseService.countTheory());
        model.addAttribute("practicalCourses", courseService.countPractical());
        return "admin/course-syllabus";
    }

    @GetMapping("/admin/add-course")
    public String addCoursePage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        model.addAttribute("facultyList", facultyRepo.findAll());
        return "admin/add-course";
    }

    @PostMapping("/admin/save-course")
    public String saveCourse(Course course) {
        courseService.save(course);
        return "redirect:/admin/coursesyllabus";
    }

    @GetMapping("/admin/edit-course/{id}")
    public String editCoursePage(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        model.addAttribute("facultyList", facultyRepo.findAll());
        return "admin/edit-course";
    }

    @PostMapping("/admin/update-course")
    public String updateCourse(Course course) {
        courseService.save(course);
        return "redirect:/admin/coursesyllabus";
    }

    @GetMapping("/admin/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/admin/coursesyllabus";
    }
}