package com.fa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.api.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	List<Article> findByParentArticleIsNull();
	
	Article findByName(String name);
	
	List<Article> findByIdAndNameContainingIgnoreCase(Long id, String name);
	
	List<Article> findAllByNameContainingIgnoreCase(String name);
	
}
