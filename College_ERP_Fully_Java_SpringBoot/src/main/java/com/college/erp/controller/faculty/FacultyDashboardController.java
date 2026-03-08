package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AttendanceService;
import com.college.erp.service.ExamService;
import com.college.erp.service.LeaveApplicationService;
import com.college.erp.service.LessonPlanService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FacultyDashboardController {

    private final FacultyRepository       facultyRepo;
    private final StudentRepository       studentRepo;
    private final AttendanceService       attendanceService;
    private final LessonPlanService       planService;
    private final ExamService             examService;
    private final LeaveApplicationService leaveService;

    public FacultyDashboardController(FacultyRepository       facultyRepo,
                                      StudentRepository        studentRepo,
                                      AttendanceService        attendanceService,
                                      LessonPlanService        planService,
                                      ExamService              examService,
                                      LeaveApplicationService  leaveService) {
        this.facultyRepo       = facultyRepo;
        this.studentRepo       = studentRepo;
        this.attendanceService = attendanceService;
        this.planService       = planService;
        this.examService       = examService;
        this.leaveService      = leaveService;
    }

    @GetMapping("/faculty/dashboard")
    public String facultyDashboard(Authentication auth, Model model) {

        String  username = auth.getName();
        Faculty faculty  = facultyRepo.findByUsername(username);
        model.addAttribute("faculty", faculty);

        if (faculty != null) {

            // Students in same department
            List<com.college.erp.model.Student> deptStudents = studentRepo.findAll()
                    .stream()
                    .filter(s -> faculty.getDepartment().equals(s.getDepartment()))
                    .toList();
            model.addAttribute("totalStudents", deptStudents.size());

            // Lesson plans count
            model.addAttribute("totalPlans",
                    planService.getByFaculty(username).size());

            // Upcoming exams count
            model.addAttribute("upcomingExams",
                    examService.countByStatus(username, "Upcoming"));

            // Pending leave applications count
            model.addAttribute("pendingLeaves",
                    leaveService.countByStatus(username, "Pending"));

            // Recent lesson plans for overview (last 4)
            var plans = planService.getByFaculty(username);
            int pCut  = Math.max(0, plans.size() - 4);
            model.addAttribute("recentPlans", plans.subList(pCut, plans.size()));

            // Recent exams for overview (last 4)
            var exams = examService.getByFaculty(username);
            int eCut  = Math.max(0, exams.size() - 4);
            model.addAttribute("recentExams", exams.subList(eCut, exams.size()));
        }

        // ✅ FIX: was "faculty-dashboard", now correctly inside faculty/ folder
        return "faculty/dashboard";
    }
}