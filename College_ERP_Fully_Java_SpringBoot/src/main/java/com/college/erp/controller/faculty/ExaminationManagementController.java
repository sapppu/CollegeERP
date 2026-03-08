package com.college.erp.controller.faculty;

import com.college.erp.model.Exam;
import com.college.erp.model.Faculty;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.ExamService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExaminationManagementController {

    private final ExamService        examService;
    private final FacultyRepository  facultyRepo;
    private final DepartmentRepository deptRepo;

    public ExaminationManagementController(ExamService examService,
                                           FacultyRepository facultyRepo,
                                           DepartmentRepository deptRepo) {
        this.examService  = examService;
        this.facultyRepo  = facultyRepo;
        this.deptRepo     = deptRepo;
    }

    @GetMapping("/faculty/examinationmanagement")
    public String view(Authentication auth,
                       @RequestParam(required = false) String filterStatus,
                       @RequestParam(required = false) String filterType,
                       Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty",     faculty);
        model.addAttribute("allExams",    examService.getByFaculty(username));
        model.addAttribute("totalExams",  examService.countTotal(username));
        model.addAttribute("upcomingCount", examService.countByStatus(username, "Upcoming"));
        model.addAttribute("ongoingCount",  examService.countByStatus(username, "Ongoing"));
        model.addAttribute("completedCount",examService.countByStatus(username, "Completed"));
        model.addAttribute("departments", deptRepo.findAll());

        if (filterStatus != null && !filterStatus.isBlank()) {
            model.addAttribute("filteredExams",
                    examService.getByFacultyAndStatus(username, filterStatus));
            model.addAttribute("filterStatus", filterStatus);
        } else if (filterType != null && !filterType.isBlank()) {
            model.addAttribute("filteredExams",
                    examService.getByFacultyAndType(username, filterType));
            model.addAttribute("filterType", filterType);
        }

        return "faculty/examination-management";
    }

    @PostMapping("/faculty/save-exam")
    public String saveExam(Authentication auth, Exam exam) {
        exam.setFacultyUsername(auth.getName());
        examService.save(exam);
        return "redirect:/faculty/examinationmanagement?success";
    }

    @GetMapping("/faculty/edit-exam/{id}")
    public String editExam(@PathVariable Long id, Model model, Authentication auth) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        model.addAttribute("exam",        examService.getById(id));
        model.addAttribute("faculty",     faculty);
        model.addAttribute("departments", deptRepo.findAll());
        return "faculty/edit-exam";
    }

    @PostMapping("/faculty/update-exam")
    public String updateExam(Authentication auth, Exam exam) {
        exam.setFacultyUsername(auth.getName());
        examService.save(exam);
        return "redirect:/faculty/examinationmanagement";
    }

    @GetMapping("/faculty/delete-exam/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.delete(id);
        return "redirect:/faculty/examinationmanagement";
    }
}