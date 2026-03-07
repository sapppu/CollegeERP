package com.college.erp.controller.finance;

import com.college.erp.model.EventSeminar;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.EventSeminarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventSeminarController {

    private final EventSeminarService eventService;
    private final DepartmentRepository deptRepo;

    public EventSeminarController(EventSeminarService eventService,
                                  DepartmentRepository deptRepo) {
        this.eventService = eventService;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/eventseminar")
    public String viewEvents(Model model) {
        model.addAttribute("list", eventService.getAll());
        model.addAttribute("totalRecords", eventService.countTotal());
        model.addAttribute("upcomingCount", eventService.countUpcoming());
        model.addAttribute("ongoingCount", eventService.countOngoing());
        model.addAttribute("completedCount", eventService.countCompleted());
        return "admin/event-seminar";
    }

    @GetMapping("/admin/add-event-seminar")
    public String addEventPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-event-seminar";
    }

    @PostMapping("/admin/save-event-seminar")
    public String saveEvent(EventSeminar event) {
        eventService.save(event);
        return "redirect:/admin/eventseminar";
    }

    @GetMapping("/admin/edit-event-seminar/{id}")
    public String editEventPage(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-event-seminar";
    }

    @PostMapping("/admin/update-event-seminar")
    public String updateEvent(EventSeminar event) {
        eventService.save(event);
        return "redirect:/admin/eventseminar";
    }

    @GetMapping("/admin/delete-event-seminar/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return "redirect:/admin/eventseminar";
    }

    @GetMapping("/admin/complete-event/{id}")
    public String completeEvent(@PathVariable Long id) {
        eventService.markCompleted(id);
        return "redirect:/admin/eventseminar";
    }

    @GetMapping("/admin/cancel-event/{id}")
    public String cancelEvent(@PathVariable Long id) {
        eventService.markCancelled(id);
        return "redirect:/admin/eventseminar";
    }
}