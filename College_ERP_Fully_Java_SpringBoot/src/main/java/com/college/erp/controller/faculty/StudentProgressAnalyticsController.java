package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.model.Student;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AttendanceService;
import com.college.erp.service.InternalMarkService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentProgressAnalyticsController {

    private final FacultyRepository   facultyRepo;
    private final StudentRepository   studentRepo;
    private final AttendanceService   attendanceService;
    private final InternalMarkService markService;

    public StudentProgressAnalyticsController(FacultyRepository   facultyRepo,
                                              StudentRepository   studentRepo,
                                              AttendanceService   attendanceService,
                                              InternalMarkService markService) {
        this.facultyRepo       = facultyRepo;
        this.studentRepo       = studentRepo;
        this.attendanceService = attendanceService;
        this.markService       = markService;
    }

    @GetMapping("/faculty/studentprogressanalytics")
    public String view(Authentication auth,
                       @RequestParam(required = false) String selectedUsername,
                       Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty", faculty);

        // All students in same dept
        List<Student> students = faculty != null
                ? studentRepo.findAll().stream()
                .filter(s -> faculty.getDepartment().equals(s.getDepartment()))
                .toList()
                : List.of();
        model.addAttribute("students", students);
        model.addAttribute("totalStudents", students.size());

        // Dept-level stats
        long totalAttRec = students.stream()
                .mapToLong(s -> attendanceService.getByUsername(s.getUsername()).size())
                .sum();
        long totalPresent = students.stream()
                .mapToLong(s -> attendanceService.getByUsername(s.getUsername()).stream()
                        .filter(a -> "Present".equals(a.getStatus())).count())
                .sum();
        double deptAttPct = totalAttRec > 0
                ? Math.round((totalPresent * 100.0 / totalAttRec) * 10) / 10.0
                : 0.0;
        model.addAttribute("deptAttPct", deptAttPct);

        double deptAvgMarks = markService.getAvgMarks(username);
        model.addAttribute("deptAvgMarks", deptAvgMarks);

        // Selected student detail
        if (selectedUsername != null && !selectedUsername.isBlank()) {
            Student selected = students.stream()
                    .filter(s -> selectedUsername.equals(s.getUsername()))
                    .findFirst().orElse(null);
            model.addAttribute("selected", selected);
            model.addAttribute("selectedUsername", selectedUsername);

            if (selected != null) {
                var attList  = attendanceService.getByUsername(selectedUsername);
                var markList = markService.getByStudent(selectedUsername);

                long present = attList.stream().filter(a -> "Present".equals(a.getStatus())).count();
                long absent  = attList.stream().filter(a -> "Absent".equals(a.getStatus())).count();
                long late    = attList.stream().filter(a -> "Late".equals(a.getStatus())).count();
                long total   = attList.size();
                double attPct = total > 0
                        ? Math.round((present * 100.0 / total) * 10) / 10.0
                        : 0.0;

                model.addAttribute("attList",   attList);
                model.addAttribute("markList",  markList);
                model.addAttribute("present",   present);
                model.addAttribute("absent",    absent);
                model.addAttribute("late",      late);
                model.addAttribute("totalAtt",  total);
                model.addAttribute("attPct",    attPct);

                double avgMark = markList.isEmpty() ? 0.0
                        : Math.round(markList.stream()
                        .mapToDouble(m -> m.getTotalMarks() > 0
                                ? m.getMarksObtained() / m.getTotalMarks() * 100
                                : 0)
                        .average().orElse(0.0) * 10) / 10.0;
                model.addAttribute("avgMark", avgMark);
            }
        }

        return "faculty/student-progress-analytics";
    }
}