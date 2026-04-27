package com.college.erp.controller.faculty;

import com.college.erp.model.Attendance;
import com.college.erp.model.Course;
import com.college.erp.model.Faculty;
import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.AttendanceService;
import com.college.erp.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AttendanceManagementController {

    private final AttendanceService attendanceService;
    private final FacultyRepository facultyRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository  courseRepo;
    private final ExcelService      excelService;

    public AttendanceManagementController(AttendanceService attendanceService,
                                          FacultyRepository facultyRepo,
                                          StudentRepository studentRepo,
                                          CourseRepository  courseRepo,
                                          ExcelService      excelService) {
        this.attendanceService = attendanceService;
        this.facultyRepo       = facultyRepo;
        this.studentRepo       = studentRepo;
        this.courseRepo        = courseRepo;
        this.excelService      = excelService;
    }

    @GetMapping("/faculty/attendancemanagement")
    public String viewAttendance(Authentication auth,
                                 @RequestParam(required = false) String subject,
                                 @RequestParam(required = false) String date,
                                 Model model) {

        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty", faculty);

        List<Attendance> allRecords = attendanceService.getByFaculty(username);
        model.addAttribute("allRecords",   allRecords);
        model.addAttribute("totalRecords", allRecords.size());

        model.addAttribute("presentCount", attendanceService.countFacultyPresent(username));
        model.addAttribute("absentCount",  attendanceService.countFacultyAbsent(username));

        List<Course> myCourses = (faculty != null)
                ? courseRepo.findByFacultyName(faculty.getName())
                : List.of();

        model.addAttribute("courses", myCourses);
        model.addAttribute("dates", attendanceService.getDates(username));

        if (subject != null && !subject.isBlank()) {
            model.addAttribute("filteredRecords",
                    attendanceService.getByFacultyAndSubject(username, subject));
            model.addAttribute("filterSubject", subject);
        } else if (date != null && !date.isBlank()) {
            model.addAttribute("filteredRecords",
                    attendanceService.getByFacultyAndDate(username, date));
            model.addAttribute("filterDate", date);
        }

        List<Student> students = (faculty != null)
                ? studentRepo.findAll().stream()
                .filter(s -> faculty.getDepartment().equals(s.getDepartment()))
                .toList()
                : List.of();

        model.addAttribute("students", students);
        model.addAttribute("today", LocalDate.now().toString());

        return "faculty/attendance-management";
    }

    @PostMapping("/faculty/mark-attendance")
    public String markAttendance(Authentication auth,
                                 @RequestParam String subject,
                                 @RequestParam String attendanceDate,
                                 @RequestParam List<String> usernames,
                                 @RequestParam List<String> statuses) {

        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);

        if (usernames == null || statuses == null) {
            return "redirect:/faculty/attendancemanagement?error";
        }

        int count = Math.min(usernames.size(), statuses.size());

        for (int i = 0; i < count; i++) {
            String stuUsername = usernames.get(i);
            Student student = studentRepo.findAll().stream()
                    .filter(s -> stuUsername.equals(s.getUsername()))
                    .findFirst().orElse(null);
            if (student == null) continue;

            Attendance att = new Attendance();
            att.setStudentUsername(stuUsername);
            att.setStudentName(student.getName());
            att.setDepartment(faculty != null ? faculty.getDepartment() : "");
            att.setYear(student.getYear());
            att.setSubject(subject);
            att.setDate(attendanceDate);
            att.setStatus(statuses.get(i));
            att.setMarkedBy(username);
            attendanceService.save(att);
        }

        return "redirect:/faculty/attendancemanagement?success";
    }

    @GetMapping("/faculty/delete-attendance/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.delete(id);
        return "redirect:/faculty/attendancemanagement";
    }

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/faculty/export-attendance")
    public void exportAttendance(Authentication auth, HttpServletResponse response) throws IOException {
        String username = auth.getName();
        String[] headers = {"ID", "Student Username", "Student Name", "Department", "Year", "Subject", "Date", "Status"};
        List<Object[]> rows = new ArrayList<>();
        for (Attendance a : attendanceService.getByFaculty(username)) {
            rows.add(new Object[]{a.getId(), a.getStudentUsername(), a.getStudentName(), a.getDepartment(), a.getYear(), a.getSubject(), a.getDate(), a.getStatus()});
        }
        excelService.exportToExcel(response, "attendance", "Attendance", headers, rows);
    }
}