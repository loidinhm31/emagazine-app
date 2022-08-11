package com.emagazine.web.controller.client;

import java.util.List;
import java.util.Map;

import com.emagazine.web.model.PostInstruction;
import com.emagazine.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping("/home")
	public String showHome(Model theModel) {		
		
		Map<String, List<PostInstruction>> postMap = postService.fetchHomePosts();
		
		theModel.addAttribute("title", "Home");

		theModel.addAttribute("postMap", postMap);
		
		return "client/home";
	}
	
	
	
}
