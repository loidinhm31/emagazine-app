package com.fa.mockweb.controller.client;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fa.mockweb.model.ArticleDetails;
import com.fa.mockweb.model.Post;
import com.fa.mockweb.model.PostInstruction;
import com.fa.mockweb.service.ArticleService;
import com.fa.mockweb.service.PostService;

@Controller
public class PageController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private PostService postService;

	@GetMapping("/page")
	public String showMainPage(Model theModel,
			@RequestParam("id") Long articleId, 
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		
		ArticleDetails currArticle = articleService.fetchArticle(articleId);

		List<PostInstruction> posts = postService.fetchPostsByArticleId(articleId);
		
		Post thePost = null;
		if (posts != null) {	
			// Get 0 index post (post for the current article)
			thePost = postService.fetchPostsById(posts.get(0).getId());
		
		}
	

		if (!currArticle.isRoot()) {	
			// Pagination for non-root article
			int currentPage = page.orElse(1);
			int pageSize = size.orElse(5);
			
			Page<PostInstruction> postInstructions = postService.fetchPostsByParentArticle(currArticle.getId(),
					PageRequest.of(currentPage, pageSize));

			theModel.addAttribute("postIntros", postInstructions);
			
			if (postInstructions != null) {
				int totalPages = postInstructions.getTotalPages();
				
				if (totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					theModel.addAttribute("pageNumbers", pageNumbers);
				}
			} 
			
		}
		
		theModel.addAttribute("title", currArticle.getName());
		theModel.addAttribute("post", thePost);
		theModel.addAttribute("mainArticle", currArticle);
		
		
		return "client/main_page";
	
	}

	
	@GetMapping("/sub")
	public String showSubPage(@RequestParam("id") Long articleId, Model theModel) {
		
		ArticleDetails currArticle = articleService.fetchArticle(articleId);

		List<PostInstruction> posts = postService.fetchPostsByArticleId(articleId);
		
		Post thePost = null;
		if (posts != null) {	
			// Get 0 index post (post for the current article)
			thePost = postService.fetchPostsById(posts.get(0).getId());
		
		}
		
		
		if (!currArticle.isRoot()) {
			
			if (posts != null) {
				// Then remove 0 index post, add list of posts for table of content
				posts.remove(0);

				theModel.addAttribute("postIntros", posts);
			}
			
		} else {
			// Get List of post from current article
			List<PostInstruction> postInstructions = postService.fetchTopPostsForReview(currArticle.getId());

			theModel.addAttribute("postIntros", postInstructions);
		}
		
		
		theModel.addAttribute("title", currArticle.getName());
		theModel.addAttribute("post", thePost);
		theModel.addAttribute("subArticle", currArticle);
		
		return "client/sub_page";
	}

	
	@GetMapping("/shell")
	public String showShell(@RequestParam("id") Long articleId, 
							Model theModel) {
	
		// Get posts of the current article
		List<PostInstruction> posts = postService.fetchPostsByArticleId(articleId);
		
		
		Post thePost = null;
		if (posts != null) {
			// Get 0 index post (post for the current article)		
			thePost = postService.fetchPostsById(posts.get(0).getId());

			// Then remove 0 index post, add list of posts for table of content
			posts.remove(0);
		}
		
		// Get current article
		ArticleDetails currArticle = articleService.fetchArticle(articleId);
		
		
		// Get parent article
		ArticleDetails parentArticle = articleService.fetchArticle(currArticle.getParentArticle().getId());
		
		
		theModel.addAttribute("title", thePost.getTitle());
		theModel.addAttribute("parentArticle", parentArticle);
		theModel.addAttribute("post", thePost);
		theModel.addAttribute("posts", posts);
		
		return "client/shell_page";
	}



}
