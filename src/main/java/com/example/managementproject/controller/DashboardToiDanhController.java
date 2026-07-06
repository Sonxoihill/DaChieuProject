package com.example.managementproject.controller;

import com.example.managementproject.dto.DashboardToiDanhRequest;
import com.example.managementproject.dto.DashboardToiDanhResponse;
import com.example.managementproject.service.DashboardToiDanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardToiDanhController {
    @Autowired
    DashboardToiDanhService dashboardToiDanhService;

    @GetMapping("/toi-danh")
    public String viewDashboard(@ModelAttribute("filters") DashboardToiDanhRequest filters, Model model) {
        DashboardToiDanhResponse data = dashboardToiDanhService.getToiDanhDashboard(filters);
        model.addAttribute("dashboardData", data);
        return "dashboard_toi_danh";
    }
}
