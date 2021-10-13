package com.fa.mockweb.controller.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fa.mockweb.model.PostInstruction;
import com.fa.mockweb.service.PostService;

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
