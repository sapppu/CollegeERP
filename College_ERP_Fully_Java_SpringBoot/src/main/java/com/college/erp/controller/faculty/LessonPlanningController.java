package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.model.LessonPlan;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.LessonPlanService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LessonPlanningController {

    private final LessonPlanService planService;
    private final FacultyRepository facultyRepo;
    private final DepartmentRepository deptRepo;

    public LessonPlanningController(LessonPlanService planService,
                                    FacultyRepository facultyRepo,
                                    DepartmentRepository deptRepo) {
        this.planService  = planService;
        this.facultyRepo  = facultyRepo;
        this.deptRepo     = deptRepo;
    }

    @GetMapping("/faculty/lessonplanning")
    public String viewPlans(Authentication auth, Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty", faculty);
        model.addAttribute("plans",        planService.getByFaculty(username));
        model.addAttribute("totalPlans",   planService.getByFaculty(username).size());
        model.addAttribute("completedCount", planService.countByStatus(username, "Completed"));
        model.addAttribute("plannedCount",   planService.countByStatus(username, "Planned"));
        model.addAttribute("departments",  deptRepo.findAll());
        return "faculty/lesson-planning";
    }

    @PostMapping("/faculty/save-plan")
    public String savePlan(Authentication auth, LessonPlan plan) {
        plan.setFacultyUsername(auth.getName());
        planService.save(plan);
        return "redirect:/faculty/lessonplanning";
    }

    @GetMapping("/faculty/edit-plan/{id}")
    public String editPlan(@PathVariable Long id, Model model, Authentication auth) {
        model.addAttribute("plan", planService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        model.addAttribute("faculty", faculty);
        return "faculty/edit-plan";
    }

    @PostMapping("/faculty/update-plan")
    public String updatePlan(LessonPlan plan, Authentication auth) {
        plan.setFacultyUsername(auth.getName());
        planService.save(plan);
        return "redirect:/faculty/lessonplanning";
    }

    @GetMapping("/faculty/delete-plan/{id}")
    public String deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return "redirect:/faculty/lessonplanning";
    }
}