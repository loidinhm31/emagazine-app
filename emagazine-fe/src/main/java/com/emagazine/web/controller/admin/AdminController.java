package com.emagazine.web.controller.admin;

import org.springframework.stereotype.Controller;

@Controller
public class AdminController {

	public String showAdmin() {
		return "redirect:/admin/dashboard";
	}
	
}
