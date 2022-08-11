package com.emagazine.web.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emagazine.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.emagazine.web.model.ArticleDetails;

@Component
public class NavigationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ArticleService articleService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		List<ArticleDetails> mainArticles = articleService.fetchMainArticles();
		
		request.setAttribute("articles", mainArticles);
		
		
		return true;
	}
}
