package com.college.erp.controller.admin;

import com.college.erp.model.AcademicEvent;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.AcademicEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AcademicCalendarController {

    private final AcademicEventService service;
    private final DepartmentRepository deptRepo;

    public AcademicCalendarController(AcademicEventService service,
                                      DepartmentRepository deptRepo) {
        this.service = service;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/academiccalendar")
    public String viewCalendar(Model model) {
        model.addAttribute("list", service.getAll());
        model.addAttribute("totalEvents", service.getAll().size());
        model.addAttribute("upcomingEvents", service.countUpcoming());
        model.addAttribute("examCount", service.countByType("Exam"));
        model.addAttribute("holidayCount", service.countByType("Holiday"));
        return "admin/academic-calendar";
    }

    @GetMapping("/admin/add-event")
    public String addEventPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-event";
    }

    @PostMapping("/admin/save-event")
    public String saveEvent(AcademicEvent event) {
        service.save(event);
        return "redirect:/admin/academiccalendar";
    }

    @GetMapping("/admin/edit-event/{id}")
    public String editEventPage(@PathVariable Long id, Model model) {
        model.addAttribute("event", service.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-event";
    }

    @PostMapping("/admin/update-event")
    public String updateEvent(AcademicEvent event) {
        service.save(event);
        return "redirect:/admin/academiccalendar";
    }

    @GetMapping("/admin/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/academiccalendar";
    }
}