package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.model.LeaveApplication;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.LeaveApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class FacultyLeaveController {

    private final LeaveApplicationService leaveService;
    private final FacultyRepository       facultyRepo;

    public FacultyLeaveController(LeaveApplicationService leaveService,
                                  FacultyRepository facultyRepo) {
        this.leaveService = leaveService;
        this.facultyRepo  = facultyRepo;
    }

    @GetMapping("/faculty/facultyleave")
    public String view(Authentication auth,
                       @RequestParam(required = false) String filterStatus,
                       Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty",       faculty);
        model.addAttribute("allLeaves",     leaveService.getByFaculty(username));
        model.addAttribute("totalLeaves",   leaveService.countTotal(username));
        model.addAttribute("pendingCount",  leaveService.countByStatus(username, "Pending"));
        model.addAttribute("approvedCount", leaveService.countByStatus(username, "Approved"));
        model.addAttribute("rejectedCount", leaveService.countByStatus(username, "Rejected"));
        model.addAttribute("today",         LocalDate.now().toString());

        if (filterStatus != null && !filterStatus.isBlank()) {
            model.addAttribute("filteredLeaves",
                    leaveService.getByFacultyAndStatus(username, filterStatus));
            model.addAttribute("filterStatus", filterStatus);
        }

        return "faculty/faculty-leave";
    }

    @PostMapping("/faculty/apply-leave")
    public String applyLeave(Authentication auth, LeaveApplication leave) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        leave.setFacultyUsername(username);
        leave.setFacultyName(faculty != null ? faculty.getName() : username);
        leave.setDepartment(faculty != null ? faculty.getDepartment() : "");
        leave.setStatus("Pending");
        leave.setAppliedOn(LocalDate.now().toString());
        leaveService.save(leave);
        return "redirect:/faculty/facultyleave?success";
    }

    @GetMapping("/faculty/cancel-leave/{id}")
    public String cancelLeave(@PathVariable Long id, Authentication auth) {
        LeaveApplication leave = leaveService.getById(id);
        if (leave.getFacultyUsername().equals(auth.getName())
                && "Pending".equals(leave.getStatus())) {
            leave.setStatus("Cancelled");
            leaveService.save(leave);
        }
        return "redirect:/faculty/facultyleave";
    }

    @GetMapping("/faculty/delete-leave/{id}")
    public String deleteLeave(@PathVariable Long id) {
        leaveService.delete(id);
        return "redirect:/faculty/facultyleave";
    }
}