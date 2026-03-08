package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.model.Research;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.ResearchService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResearchPublicationController {

    private final ResearchService    researchService;
    private final FacultyRepository  facultyRepo;

    public ResearchPublicationController(ResearchService researchService,
                                         FacultyRepository facultyRepo) {
        this.researchService = researchService;
        this.facultyRepo     = facultyRepo;
    }

    @GetMapping("/faculty/researchpublication")
    public String view(Authentication auth,
                       @RequestParam(required = false) String filterType,
                       @RequestParam(required = false) String filterStatus,
                       Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty",        faculty);
        model.addAttribute("allResearch",    researchService.getByFaculty(username));
        model.addAttribute("totalCount",     researchService.countTotal(username));
        model.addAttribute("journalCount",   researchService.countByType(username, "Journal Paper"));
        model.addAttribute("conferenceCount",researchService.countByType(username, "Conference Paper"));
        model.addAttribute("bookCount",      researchService.countByType(username, "Book Chapter"));

        if (filterType != null && !filterType.isBlank()) {
            model.addAttribute("filteredResearch",
                    researchService.getByFacultyAndType(username, filterType));
            model.addAttribute("filterType", filterType);
        } else if (filterStatus != null && !filterStatus.isBlank()) {
            model.addAttribute("filteredResearch",
                    researchService.getByFacultyAndStatus(username, filterStatus));
            model.addAttribute("filterStatus", filterStatus);
        }

        return "faculty/research-publication";
    }

    @PostMapping("/faculty/save-research")
    public String saveResearch(Authentication auth, Research research) {
        research.setFacultyUsername(auth.getName());
        researchService.save(research);
        return "redirect:/faculty/researchpublication?success";
    }

    @GetMapping("/faculty/edit-research/{id}")
    public String editResearch(@PathVariable Long id, Model model, Authentication auth) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        model.addAttribute("research", researchService.getById(id));
        model.addAttribute("faculty",  faculty);
        return "faculty/edit-research";
    }

    @PostMapping("/faculty/update-research")
    public String updateResearch(Authentication auth, Research research) {
        research.setFacultyUsername(auth.getName());
        researchService.save(research);
        return "redirect:/faculty/researchpublication";
    }

    @GetMapping("/faculty/delete-research/{id}")
    public String deleteResearch(@PathVariable Long id) {
        researchService.delete(id);
        return "redirect:/faculty/researchpublication";
    }
}