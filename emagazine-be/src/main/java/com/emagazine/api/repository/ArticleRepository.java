package com.emagazine.api.repository;

import java.util.List;

import com.emagazine.api.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByParentArticleIsNull();

    Article findByName(String name);

    List<Article> findByIdAndNameContainingIgnoreCase(Long id, String name);

    List<Article> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM `articles` a " +
            "WHERE a.parent_id = :parentId", nativeQuery = true)
    List<Article> findByParentId(@Param("parentId") Long parentId);

    @Query(value = "SELECT count_top.id " +
            "FROM (SELECT a.*, sum(p.count_view) AS count_top_view " +
            "      FROM posts p " +
            "               INNER JOIN articles a ON p.article_id = a.id " +
            "      WHERE is_root = false " +
            "      GROUP BY a.id " +
            "      ORDER BY count_top_view DESC " +
            "      LIMIT :top) as count_top;", nativeQuery = true)
    List<Long> findByTopNotRootAndView(@Param("top") int top);
}
