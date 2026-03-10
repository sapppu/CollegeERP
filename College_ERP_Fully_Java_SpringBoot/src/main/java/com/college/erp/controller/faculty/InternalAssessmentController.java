package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.model.InternalMark;
import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.InternalMarkService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InternalAssessmentController {

    private final InternalMarkService markService;
    private final FacultyRepository   facultyRepo;
    private final StudentRepository   studentRepo;
    private final CourseRepository    courseRepo;

    public InternalAssessmentController(InternalMarkService markService,
                                        FacultyRepository   facultyRepo,
                                        StudentRepository   studentRepo,
                                        CourseRepository    courseRepo) {
        this.markService = markService;
        this.facultyRepo = facultyRepo;
        this.studentRepo = studentRepo;
        this.courseRepo  = courseRepo;
    }

    @GetMapping("/faculty/internalassessment")
    public String view(Authentication auth,
                       @RequestParam(required = false) String filterSubject,
                       @RequestParam(required = false) String filterType,
                       Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty", faculty);

        List<InternalMark> allMarks = markService.getByFaculty(username);
        model.addAttribute("allMarks",     allMarks);
        model.addAttribute("totalEntries", markService.getTotalEntries(username));
        model.addAttribute("avgMarks",     markService.getAvgMarks(username));
        model.addAttribute("subjects",     markService.getSubjects(username));

        if (filterSubject != null && !filterSubject.isBlank()) {
            model.addAttribute("filteredMarks",
                    markService.getByFacultyAndSubject(username, filterSubject));
            model.addAttribute("filterSubject", filterSubject);
        } else if (filterType != null && !filterType.isBlank()) {
            model.addAttribute("filteredMarks",
                    markService.getByFacultyAndType(username, filterType));
            model.addAttribute("filterType", filterType);
        }

        List<Student> students = faculty != null
                ? studentRepo.findAll().stream()
                .filter(s -> faculty.getDepartment().equals(s.getDepartment()))
                .toList()
                : List.of();
        model.addAttribute("students", students);
        model.addAttribute("courses",
                faculty != null ? courseRepo.findByDepartment(faculty.getDepartment()) : List.of());

        return "faculty/internal-assessment";
    }

    @PostMapping("/faculty/save-marks-bulk")
    public String saveMarksBulk(Authentication auth,
                                @RequestParam String subject,
                                @RequestParam String assessmentType,
                                @RequestParam Double totalMarks,
                                @RequestParam String examDate,
                                @RequestParam List<String> usernames,
                                @RequestParam List<Double> marksObtained,
                                @RequestParam(required = false) List<String> remarks) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);

        // ✅ FIX: guard against null or mismatched list sizes
        if (usernames == null || marksObtained == null) {
            return "redirect:/faculty/internalassessment?error";
        }

        int count = Math.min(usernames.size(), marksObtained.size());

        for (int i = 0; i < count; i++) {
            String stuUsername = usernames.get(i);
            Student student = studentRepo.findAll().stream()
                    .filter(s -> stuUsername.equals(s.getUsername()))
                    .findFirst().orElse(null);
            if (student == null) continue;

            InternalMark mark = new InternalMark();
            mark.setStudentUsername(stuUsername);
            mark.setStudentName(student.getName());
            mark.setDepartment(faculty != null ? faculty.getDepartment() : "");
            mark.setYear(student.getYear());
            mark.setSubject(subject);
            mark.setAssessmentType(assessmentType);
            mark.setMarksObtained(marksObtained.get(i));
            mark.setTotalMarks(totalMarks);
            mark.setExamDate(examDate);
            // ✅ FIX: safe access on optional remarks list
            mark.setRemarks(remarks != null && i < remarks.size() ? remarks.get(i) : "");
            mark.setFacultyUsername(username);
            markService.save(mark);
        }

        return "redirect:/faculty/internalassessment?success";
    }

    @GetMapping("/faculty/edit-mark/{id}")
    public String editMark(@PathVariable Long id, Model model, Authentication auth) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        model.addAttribute("mark",    markService.getById(id));
        model.addAttribute("faculty", faculty);
        model.addAttribute("courses",
                faculty != null ? courseRepo.findByDepartment(faculty.getDepartment()) : List.of());
        return "faculty/edit-mark";
    }

    @PostMapping("/faculty/update-mark")
    public String updateMark(InternalMark mark, Authentication auth) {
        mark.setFacultyUsername(auth.getName());
        markService.save(mark);
        return "redirect:/faculty/internalassessment";
    }

    @GetMapping("/faculty/delete-mark/{id}")
    public String deleteMark(@PathVariable Long id) {
        markService.delete(id);
        return "redirect:/faculty/internalassessment";
    }
}