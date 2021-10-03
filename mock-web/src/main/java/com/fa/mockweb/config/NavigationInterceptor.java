package com.fa.mockweb.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fa.mockweb.model.ArticleDetails;
import com.fa.mockweb.service.ArticleService;

@Component
public class NavigationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ArticleService articleService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		List<ArticleDetails> mainArticles = articleService.fetchMainArticles();
//
//		request.setAttribute("articles", mainArticles);
		
		
		return true;
	}
}
