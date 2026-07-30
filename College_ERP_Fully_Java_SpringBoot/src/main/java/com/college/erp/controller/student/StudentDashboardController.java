package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.FeeStructureRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AccountSettingsService;
import com.college.erp.service.AttendanceService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDashboardController {

    private final StudentRepository      studentRepo;
    private final AttendanceService      attendanceService;
    private final CourseRepository       courseRepo;
    private final FeeStructureRepository feeRepo;
    private final AccountSettingsService accountSettings;

    public StudentDashboardController(StudentRepository      studentRepo,
                                      AttendanceService      attendanceService,
                                      CourseRepository       courseRepo,
                                      FeeStructureRepository feeRepo,
                                      AccountSettingsService accountSettings) {
        this.studentRepo       = studentRepo;
        this.attendanceService = attendanceService;
        this.courseRepo        = courseRepo;
        this.feeRepo           = feeRepo;
        this.accountSettings   = accountSettings;
    }

    @GetMapping("/student/studentdashboard")
    public String studentDashboard(Authentication auth, Model model) {

        String  username = auth.getName();
        Student student  = studentRepo.findByUsername(username);
        model.addAttribute("student", student);
        if (student != null && student.getProfilePicture() != null) {
            model.addAttribute("profilePictureUrl",
                    accountSettings.publicUrl(student.getProfilePicture()));
        }

        if (student != null) {

            // Attendance %
            long present = attendanceService.countPresent(username);
            long absent  = attendanceService.countAbsent(username);
            long classwork = attendanceService.countClasswork(username);
            long total   = attendanceService.countCountableClasses(username);
            double attPct = total > 0
                    ? Math.round((present * 100.0 / total) * 10.0) / 10.0
                    : 0.0;
            model.addAttribute("attendancePct", attPct);

            // Subject-wise attendance for progress bars
            model.addAttribute("subjectSummary",
                    attendanceService.getSubjectSummary(username));

            // Enrolled courses count (by dept)
            var courses = courseRepo.findByDepartment(student.getDepartment());
            model.addAttribute("enrolledCourses", courses.size());

            // Total pending fees
            var deptFees = feeRepo.findByDepartment(student.getDepartment());
            var allFees  = feeRepo.findByDepartment("All Departments");
            deptFees.addAll(allFees);
            double totalFees = deptFees.stream()
                    .mapToDouble(f -> f.getAmount() != null ? f.getAmount() : 0)
                    .sum();
            model.addAttribute("totalFees", totalFees);
            model.addAttribute("feeCount",  deptFees.size());
        }

        // ✅ FIX: was "student-dashboard", now correctly inside student/ folder
        return "student/dashboard";
    }
}
