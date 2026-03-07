package com.college.erp.controller.admin;

import com.college.erp.service.SystemLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SystemSettingsLogsController {

    private final SystemLogService logService;

    public SystemSettingsLogsController(SystemLogService logService) {
        this.logService = logService;
    }

    @GetMapping("/admin/systemsettingslogs")
    public String viewSystemSettings(Model model) {
        model.addAttribute("logs", logService.getAllLogs());
        model.addAttribute("totalLogs", logService.countTotal());
        model.addAttribute("infoCount", logService.countByLevel("INFO"));
        model.addAttribute("warningCount", logService.countByLevel("WARNING"));
        model.addAttribute("errorCount", logService.countByLevel("ERROR"));
        return "admin/system-settings-logs";
    }

    @PostMapping("/admin/save-manual-log")
    public String saveManualLog(@RequestParam String action,
                                @RequestParam String module,
                                @RequestParam String performedBy,
                                @RequestParam String description,
                                @RequestParam String logLevel,
                                RedirectAttributes ra) {
        logService.log(action, module, performedBy, description, logLevel);
        ra.addFlashAttribute("success", "Log entry added successfully!");
        return "redirect:/admin/systemsettingslogs";
    }

    @GetMapping("/admin/delete-log/{id}")
    public String deleteLog(@PathVariable Long id) {
        logService.deleteLog(id);
        return "redirect:/admin/systemsettingslogs";
    }

    @GetMapping("/admin/clear-all-logs")
    public String clearAllLogs(RedirectAttributes ra) {
        logService.clearAllLogs();
        ra.addFlashAttribute("success", "All logs cleared!");
        return "redirect:/admin/systemsettingslogs";
    }
}