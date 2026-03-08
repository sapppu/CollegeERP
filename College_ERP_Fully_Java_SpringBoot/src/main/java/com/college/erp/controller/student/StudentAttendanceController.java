package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AttendanceService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentAttendanceController {

    private final AttendanceService attendanceService;
    private final StudentRepository studentRepo;

    public StudentAttendanceController(AttendanceService attendanceService,
                                       StudentRepository studentRepo) {
        this.attendanceService = attendanceService;
        this.studentRepo = studentRepo;
    }

    @GetMapping("/student/attendance")
    public String viewAttendance(Authentication auth, Model model) {
        String username = auth.getName();

        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        long present = attendanceService.countPresent(username);
        long absent  = attendanceService.countAbsent(username);
        long late    = attendanceService.countLate(username);
        long total   = present + absent + late;

        double percentage = total > 0
                ? Math.round((present * 100.0 / total) * 10.0) / 10.0
                : 0.0;

        String attendanceColor = percentage >= 75 ? "#00b894" :
                percentage >= 60 ? "#e67e22" : "#e55039";

        model.addAttribute("present",         present);
        model.addAttribute("absent",          absent);
        model.addAttribute("late",            late);
        model.addAttribute("total",           total);
        model.addAttribute("percentage",      percentage);
        model.addAttribute("attendanceColor", attendanceColor);
        model.addAttribute("records",         attendanceService.getByUsername(username));
        model.addAttribute("subjectSummary",  attendanceService.getSubjectSummary(username));

        return "student/attendance";
    }
}