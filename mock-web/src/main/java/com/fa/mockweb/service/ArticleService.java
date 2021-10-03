package com.fa.mockweb.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fa.mockweb.model.ArticleDetails;
import com.fa.mockweb.model.ArticleInstruction;
import com.fa.mockweb.model.ArticleInstructionWithFullParent;
import com.fa.mockweb.model.ArticleRequest;


public interface ArticleService {
	
	List<ArticleDetails> fetchMainArticles();
		
	ArticleDetails fetchArticle(Long id);
	
	List<ArticleInstructionWithFullParent> fetchArticles();
	
	boolean saveOrUpdate(ArticleRequest theArticle, HttpSession sesssion);
	
	List<ArticleInstruction> fetchSimpleMainArticle();

	boolean deleteById(Long id, HttpSession session);
	
//	ArticleInstructionWithFullParent fetchSimpleArticleWithParent(Long id);
	
//	ArticleRequest fetchArticleForRequest(Long id);
	
	List<ArticleInstructionWithFullParent> fetchAllChildsByArticleId(Long id,String keyword, HttpSession session);
}
