package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.PlacementDriveService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlacementController {

    private final StudentRepository studentRepo;
    private final PlacementDriveService driveService;

    public PlacementController(StudentRepository studentRepo,
                               PlacementDriveService driveService) {
        this.studentRepo  = studentRepo;
        this.driveService = driveService;
    }

    @GetMapping("/student/placement")
    public String viewPlacement(Authentication auth, Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        model.addAttribute("allDrives",    driveService.getAll());
        model.addAttribute("upcomingCount", driveService.countUpcoming());
        model.addAttribute("onCampusCount", driveService.countOnCampus());
        model.addAttribute("offCampusCount",driveService.countOffCampus());
        model.addAttribute("totalDrives",   driveService.getAll().size());

        return "student/placement";
    }
}