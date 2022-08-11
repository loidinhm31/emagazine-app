package com.emagazine.api.repository;

import java.util.List;

import com.emagazine.api.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByParentArticleIsNull();

    Article findByName(String name);

    List<Article> findByIdAndNameContainingIgnoreCase(Long id, String name);

    List<Article> findAllByNameContainingIgnoreCase(String name);

}
