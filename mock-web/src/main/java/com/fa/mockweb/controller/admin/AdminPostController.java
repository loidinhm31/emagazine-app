package com.fa.mockweb.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fa.mockweb.model.ArticleInstruction;
import com.fa.mockweb.model.ArticleInstructionWithFullParent;
import com.fa.mockweb.model.Post;
import com.fa.mockweb.model.PostRequest;
import com.fa.mockweb.service.ArticleService;
import com.fa.mockweb.service.PostService;
import com.fa.mockweb.utils.RestPageHelper;

@Controller
@RequestMapping("/admin/post")
public class AdminPostController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private PostService postService;
	
	
	@GetMapping(value={"/{id}", ""})
	public String showPostManagementView(Model theModel,
			@PathVariable("id") Optional<Long> articleId,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(required = false) String keyword,
			HttpServletRequest request) {
		
		Long currId = null;
		
		if (!articleId.isPresent()) {
			List<ArticleInstruction> articles = articleService.fetchSimpleMainArticle();
			
			// Get the first article
			currId = articles.get(0).getId();
		} else {
			currId = articleId.get();
		}
		
		// Pagination
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(15);

		RestPageHelper<Post> posts = 
				postService.fetchPostByArticleId(currId, keyword, PageRequest.of(currentPage, pageSize), request.getSession());
		
		if (posts != null) {
			int totalPages = posts.getTotalPages();
			
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				theModel.addAttribute("pageNumbers", pageNumbers);
			}
		} 
		
		
		theModel.addAttribute("pageTitle", "Post Management");
		theModel.addAttribute("listPost", posts);
		theModel.addAttribute("currId", currId);
		theModel.addAttribute("activeCss", "post");
		return "admin/post";
	}
	
	
	@GetMapping("/form")
	public String showPostForm(@RequestParam(value = "post-id", required = false) Long postId,
			Model theModel) {
		
		List<ArticleInstructionWithFullParent> articles = articleService.fetchArticles();
		theModel.addAttribute("articles", articles);
		
		if(postId == null) { // add a new post
			
			theModel.addAttribute("postRequest", new PostRequest());
			theModel.addAttribute("pageTitle", "Post Detail");
			
			return "admin/post-form";
			
		} else {		// edit a post
			
			Post currPost = postService.fetchPostsById(postId);
			
			PostRequest thePost = new PostRequest(currPost.getId(),
												currPost.getTitle(),
												currPost.getThumbnail(),
												currPost.getShortDescription(),
												currPost.getContent(),
												currPost.getArticle().getId());
			
			theModel.addAttribute("pageTitle", "Post Detail");
			theModel.addAttribute("postRequest", thePost);
			
			return "admin/post-form";
		}
	}
	
	
	@PostMapping("/submit")
	public String saveOrUpdatePost(@Valid @ModelAttribute("postRequest") PostRequest thePost,
			BindingResult bindingResult,
			@RequestParam("file-upload") MultipartFile multipartFile,
			HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
			return "admin/post-form";
		}
		

		HttpSession session = request.getSession();
		
		if (!multipartFile.isEmpty()) {
			// POST image first and get server path
			String thumbnailName = postService.uploadImage(multipartFile, session);
			
			// Set thumbnail path 
			thePost.setThumbnail(thumbnailName);
			
			postService.saveOrUpdate(thePost, session);
		} else {
			postService.saveOrUpdate(thePost, session);
		}
		
		return "redirect:/admin/post";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Long postId, 
			Model theModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		
		boolean isDelete = postService.deleteById(postId, request.getSession());
		
		redirectAttributes.addFlashAttribute("isDelete", false);
	
		return "redirect:/admin/post";
	}
	
}
