package com.fa.mockweb.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fa.mockweb.model.ArticleDetails;
import com.fa.mockweb.model.ArticleInstructionWithFullParent;
import com.fa.mockweb.model.ArticleRequest;
import com.fa.mockweb.service.ArticleService;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	
	// Convert trim input strings,
	// remove leading and trailing whitespace
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	

	@GetMapping
	public String showAllArticles(Model model) {
		String title = "All Articles";

		List<ArticleInstructionWithFullParent> childArticles = 
				articleService.fetchArticles();

		model.addAttribute("pageTitle", "Article Management");
		model.addAttribute("childArticles", childArticles);
		model.addAttribute("title", title);
		model.addAttribute("activeCss", "article");
		return "admin/article";
	}

	
	@GetMapping("/{id}")
	public String showArticle(@RequestParam(required = false) String keyword,
			@PathVariable("id") Long articleId, Model model, HttpServletRequest request) {

		List<ArticleInstructionWithFullParent> childArticles = 
				articleService.fetchAllChildsByArticleId(articleId, keyword, request.getSession());
		
		// Get name of the current article
		if (!childArticles.isEmpty()) {
			String title = childArticles.get(0).getParentArticle().getName() + "'s Child Categories";
			model.addAttribute("title", title);
		}
				
		model.addAttribute("pageTitle", "Article Management");
		model.addAttribute("childArticles", childArticles);	
		model.addAttribute("id", articleId);
		return "admin/article";
	}

	
	@GetMapping(value={"/detail", "/detail/{id}"})
	public String showArticleForm(@PathVariable("id") Optional<Long> articleId, 
									Model theModel) {
		
		List<ArticleInstructionWithFullParent> articles = articleService.fetchArticles();
		// add a null parent into list
		ArticleInstructionWithFullParent nullArticle = new ArticleInstructionWithFullParent(); 
		nullArticle.setName("Empty Parent");
		articles.add(0, nullArticle);
		
		
		if (!articleId.isPresent()) { // add a new article
			
			theModel.addAttribute("pageTitle", "Create A New Article");
			theModel.addAttribute("title", "New Article");
			theModel.addAttribute("theArticle", new ArticleRequest());
			theModel.addAttribute("articles", articles);
			
			return "admin/article-form";
		} else {				// edit an article

			ArticleDetails currArticle = articleService.fetchArticle(articleId.get());
			
			// Then, remove current article from option list
			articles.removeIf(article -> article.getId() == currArticle.getId());
			
			Long parentId = null;
			if (currArticle.getParentArticle() != null) {
				 parentId = currArticle.getParentArticle().getId();
			}
			
			ArticleRequest theArticle = 
					new ArticleRequest(currArticle.getId(),
										currArticle.getName(),
										currArticle.isRoot(),
										parentId);
			
			theModel.addAttribute("pageTitle", "Modify " + theArticle.getName());
			theModel.addAttribute("title", "Article Detail");
			theModel.addAttribute("theArticle", theArticle);
			theModel.addAttribute("articles", articles);
			
			return "admin/article-form";
		}

	}

	
	@PostMapping("/submit")
	public String saveOrUpdateArticle(@Valid @ModelAttribute("theArticle") ArticleRequest theArticle,
			BindingResult bindingResult,
			Model theModel,
			HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
			
			return "admin/article-form";
		}
		
		articleService.saveOrUpdate(theArticle, request.getSession());
		
		if(theArticle.getId() != null) {
			return "redirect:/admin/article/detail?article-id=" + theArticle.getId();
			
		}

		return "redirect:/admin/article";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteArtilce(@PathVariable("id") Long articleId, 
			Model theModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		
		boolean isDelete = articleService.deleteById(articleId, request.getSession());
		
		redirectAttributes.addFlashAttribute("isDelete", false);
	
		return "redirect:/admin/article";
	}

}
