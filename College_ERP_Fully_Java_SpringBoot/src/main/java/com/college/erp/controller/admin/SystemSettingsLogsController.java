package com.college.erp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemSettingsLogsController {
    @GetMapping("/admin/systemsettingslogs")
    public String page() { return "admin/system-settings-logs"; }
}