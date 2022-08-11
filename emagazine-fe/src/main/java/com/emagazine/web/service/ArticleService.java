package com.emagazine.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.emagazine.web.model.ArticleDetails;
import com.emagazine.web.model.ArticleInstruction;
import com.emagazine.web.model.ArticleInstructionWithFullParent;
import com.emagazine.web.model.request.ArticleRequest;


public interface ArticleService {

    List<ArticleDetails> fetchMainArticles();

    ArticleDetails fetchArticle(Long id);

    List<ArticleInstructionWithFullParent> fetchArticles();

    boolean saveOrUpdate(ArticleRequest theArticle, HttpSession sesssion);

    List<ArticleInstruction> fetchSimpleMainArticle();

    boolean deleteById(Long id, HttpSession session);

//	ArticleInstructionWithFullParent fetchSimpleArticleWithParent(Long id);

//	ArticleRequest fetchArticleForRequest(Long id);

    List<ArticleInstructionWithFullParent> fetchAllChildrenByArticleId(Long id, String keyword, HttpSession session);
}
