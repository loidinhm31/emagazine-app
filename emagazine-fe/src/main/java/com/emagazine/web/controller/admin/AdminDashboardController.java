package com.emagazine.web.controller.admin;


import java.util.List;
import java.util.Map;

import com.emagazine.web.model.DataPieChart;
import com.emagazine.web.service.ChartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    ChartDataService chartDataService;

    @GetMapping(value = {"/", "/dashboard", ""})
    private String showDashboard(Model model) {
        Map<String, Integer> map = chartDataService.getMapDataColumnChart();

        List<DataPieChart> mapPieChartHalfYear = chartDataService.getPieChartArticalHalfYear();
        List<DataPieChart> list = chartDataService.getPieChartArtical();
        model.addAttribute("mapPieChart", list);
        model.addAttribute("mapPieChartHalfYear", mapPieChartHalfYear);
        model.addAttribute("postCount", map);
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("activeCss", "dashboard");
        return "admin/dashboard";
    }
}
