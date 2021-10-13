package com.fa.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fa.api.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByArticleId(Long articleId);
	
	@Query(nativeQuery = true,
			value ="SELECT * FROM posts P WHERE (P.title LIKE %:keyword% OR P.short_description LIKE %:keyword%)"
					+ " AND P.article_id IN :ids ORDER BY P.date_create DESC")
	Page<Post> findByArticleIdInAndMultipleConditions(@Param("ids") List<Long> idOfArticles, 
			@Param("keyword") String keyword, Pageable pageable);
	
	List<Post> findTop5ByArticleIdInOrderByDateCreateDesc(List<Long> articleId);
	
	List<Post> findFirst3ByArticleIdOrderByDateCreate(Long articleId);
	
	List<Post> findTop10ByIdIn(List<Long> id);
	

	Page<Post> findByArticleIdIn(List<Long> articleId, Pageable pageable);
	
	List<Post> findByDateCreateGreaterThanEqualAndDateCreateLessThanEqual(Date startDate,Date endDate);

	
}
