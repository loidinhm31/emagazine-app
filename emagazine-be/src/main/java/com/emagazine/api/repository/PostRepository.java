package com.emagazine.api.repository;

import java.util.Date;
import java.util.List;

import com.emagazine.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByArticleId(Long articleId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM `posts` as p " +
                    "WHERE (p.title LIKE %:keyword% OR p.short_description LIKE %:keyword%) "
                    + "AND p.article_id IN :ids " +
                    "ORDER BY p.date_create DESC")
    Page<Post> findByArticleIdInAndMultipleConditions(@Param("ids") List<Long> idOfArticles,
                                                      @Param("keyword") String keyword, Pageable pageable);

    List<Post> findTop5ByArticleIdInOrderByDateCreateDesc(List<Long> articleId);

    List<Post> findFirst3ByArticleIdOrderByDateCreate(Long articleId);

    List<Post> findTop10ByIdIn(List<Long> id);


    Page<Post> findByArticleIdIn(List<Long> articleId, Pageable pageable);

    List<Post> findByDateCreateGreaterThanEqualAndDateCreateLessThanEqual(Date startDate, Date endDate);

    @Query(value = "SELECT * FROM `posts` p " +
            "WHERE p.article_id IN (:articleIds)", nativeQuery = true)
    List<Post> findAllByArticleIds(@Param("articleIds") List<Long> articleIds);
}
