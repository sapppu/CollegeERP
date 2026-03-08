package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AttendanceService;
import com.college.erp.service.InternalMarkService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcademicRecordsController {

    private final StudentRepository   studentRepo;
    private final CourseRepository    courseRepo;
    private final AttendanceService   attendanceService;
    private final InternalMarkService markService;

    public AcademicRecordsController(StudentRepository   studentRepo,
                                     CourseRepository    courseRepo,
                                     AttendanceService   attendanceService,
                                     InternalMarkService markService) {
        this.studentRepo       = studentRepo;
        this.courseRepo        = courseRepo;
        this.attendanceService = attendanceService;
        this.markService       = markService;
    }

    @GetMapping("/student/academicrecords")
    public String viewRecords(Authentication auth, Model model) {
        String  username = auth.getName();
        Student student  = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        if (student != null) {

            // ── Tab 1: Courses ───────────────────────────────────────────────
            model.addAttribute("courses",
                    courseRepo.findByDepartment(student.getDepartment()));

            // ── Tab 2: Attendance (real subject-wise summary) ─────────────────
            // Object[] per row = [subject, present, absent, late, total]
            var subjectSummary = attendanceService.getSubjectSummary(username);
            model.addAttribute("subjectSummary", subjectSummary);

            // Overall attendance %
            long present = attendanceService.countPresent(username);
            long absent  = attendanceService.countAbsent(username);
            long late    = attendanceService.countLate(username);
            long total   = present + absent + late;
            double attPct = total > 0
                    ? Math.round((present * 100.0 / total) * 10.0) / 10.0
                    : 0.0;
            model.addAttribute("attPct",   attPct);
            model.addAttribute("present",  present);
            model.addAttribute("absent",   absent);
            model.addAttribute("late",     late);
            model.addAttribute("totalAtt", total);

            // ── Tab 3: Internal Marks (real from DB) ──────────────────────────
            var marks = markService.getByStudent(username);
            model.addAttribute("marks", marks);

            // Average marks percentage
            double avgMarkPct = marks.isEmpty() ? 0.0
                    : Math.round(marks.stream()
                    .mapToDouble(m -> m.getTotalMarks() > 0
                            ? m.getMarksObtained() / m.getTotalMarks() * 100
                            : 0)
                    .average().orElse(0.0) * 10) / 10.0;
            model.addAttribute("avgMarkPct",   avgMarkPct);
            model.addAttribute("totalMarksEntries", marks.size());
        }

        return "student/academic-records";
    }
}