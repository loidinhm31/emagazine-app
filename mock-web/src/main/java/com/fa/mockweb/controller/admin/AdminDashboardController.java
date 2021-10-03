package com.fa.mockweb.controller.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fa.mockweb.model.DataPieChart;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

	@GetMapping(value = {"/", "/dashboard",""})
	private String showDashboard(Model model) {
		Map<String,Integer > map = new LinkedHashMap<String, Integer>();
		map.put("Mar", 30);
		map.put("Apr", 40);
		map.put("May", 35);
		map.put("Jun", 20);
		map.put("July", 50);
		List<DataPieChart> list = new ArrayList<DataPieChart>();
		list.add(new DataPieChart("pass", 50));
		list.add(new DataPieChart("fail", 50));
		model.addAttribute("pass", 50);
		model.addAttribute("fail", 50);
		model.addAttribute("mapPieChart", list);
		model.addAttribute("postCount", map);
		model.addAttribute("pageTitle", "Dashboard");
		model.addAttribute("activeCss", "dashboard");
		return "admin/dashboard";
	}
}
