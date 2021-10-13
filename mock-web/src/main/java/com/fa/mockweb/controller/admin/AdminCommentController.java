package com.fa.mockweb.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fa.mockweb.model.Comment;
import com.fa.mockweb.model.CommentForListView;
import com.fa.mockweb.model.PostInstruction;
import com.fa.mockweb.service.CommentService;
import com.fa.mockweb.service.PostService;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	PostService postService;
	
	//main page when visit comment management page
	@GetMapping
	public String commentPage(@RequestParam(required = false) String keyword,
			Model model, HttpServletRequest request) {
		List<PostInstruction> posts = postService.fetchPostByComment(request.getSession());
		List<CommentForListView> comments = commentService.findAll(keyword, request.getSession());
		
		model.addAttribute("title", "All comments");
		model.addAttribute("comments", comments);
		model.addAttribute("posts", posts);
		model.addAttribute("activeCss", "comment");
		
		return "admin/comment";
	}
	
	
	//get comments by target post on management page
	@GetMapping("/{id}/post")
	public String commentPage(
			@PathVariable("id") Long id,
			@RequestParam(required = false) String keyword,
			Model model,
			HttpServletRequest request) {
		List<PostInstruction> posts = postService.fetchPostByComment(request.getSession());
		List<CommentForListView> comments = 
				commentService.findByPostIdForAdminPage(id, keyword, request.getSession());
		
		model.addAttribute("title", "All comments");
		model.addAttribute("comments", comments);
		model.addAttribute("posts", posts);
		model.addAttribute("currId", id);
		
		return "admin/comment";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpServletRequest request) {
		commentService.deleteById(id, request.getSession());
		return "redirect:/admin/comment";
	}
	
	
	@GetMapping("/{id}")
	public String viewDetail(@PathVariable("id") Long id, Model model) {
		Comment comment = commentService.findById(id);
		
		model.addAttribute("pageTitle", "Comment Detail");
		model.addAttribute("title", "Comment Detail");
		model.addAttribute("comment", comment);
		
		return "admin/comment-detail";
	}
}
