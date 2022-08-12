package com.emagazine.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.emagazine.api.entity.Post;
import com.emagazine.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emagazine.api.entity.Article;
import com.emagazine.api.model.ArticleDetailsDTO;
import com.emagazine.api.model.ArticleInstructionDTO;
import com.emagazine.api.model.ArticleInstructionWithFullParentDTO;
import com.emagazine.api.model.ArticleInstructionWithParentIdDTO;
import com.emagazine.api.model.ArticleRequestDTO;
import com.emagazine.api.repository.ArticleRepository;
import com.emagazine.api.service.ArticleService;
import com.emagazine.api.utils.ObjectMapperUtils;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository articleRepository;


    private final PostRepository postRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, PostRepository postRepository) {
        this.articleRepository = articleRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<ArticleDetailsDTO> findMainArticles() {
        // Get main articles with null parent
        List<Article> mainArticles = articleRepository.findByParentArticleIsNull();

        if (!mainArticles.isEmpty()) {

            List<ArticleDetailsDTO> articleJsons = ObjectMapperUtils.mapAll(mainArticles, ArticleDetailsDTO.class);

            return articleJsons;
        }
        return null;
    }


    @Override
    public List<ArticleInstructionDTO> findSimpleMainArticles() {

        List<Article> mainArticles = articleRepository.findByParentArticleIsNull();

        if (!mainArticles.isEmpty()) {

            List<ArticleInstructionDTO> articleJsons = ObjectMapperUtils.mapAll(mainArticles, ArticleInstructionDTO.class);

            return articleJsons;
        }
        return null;
    }

    @Override
    public ArticleDetailsDTO findArticleById(Long id) {
        Optional<Article> theArticle = articleRepository.findById(id);

        if (theArticle.isPresent()) {

            ArticleDetailsDTO articleJson = ObjectMapperUtils.map(theArticle.get(), ArticleDetailsDTO.class);

            return articleJson;
        }
        return null;
    }

    @Override
    public List<ArticleInstructionWithFullParentDTO> findAllArticles() {

        List<Article> listArticle = articleRepository.findAll();

        List<ArticleInstructionWithFullParentDTO> articles = ObjectMapperUtils.mapAll(listArticle, ArticleInstructionWithFullParentDTO.class);
        return articles;
    }


    @Override
    public List<ArticleInstructionWithFullParentDTO> findAllChildrenById(Long articleId, String keyword) {
        if (keyword == null) {
            keyword = "";
        }
        List<ArticleInstructionWithFullParentDTO> listChild = new ArrayList<>();
        ArticleDetailsDTO article = findArticleById(articleId);

        listChild = getAllChildren(article, listChild, keyword);
        return listChild;
    }

    private List<ArticleInstructionWithFullParentDTO> getAllChildren(ArticleDetailsDTO article, List<ArticleInstructionWithFullParentDTO> listArticle, String keyword) {

        List<ArticleDetailsDTO> listArticlesDetail = article.getChildArticles();
        if (listArticlesDetail != null) {
            for (ArticleDetailsDTO articleDetails : listArticlesDetail) {
                ArticleInstructionWithFullParentDTO articleInstruction = ObjectMapperUtils.map(articleDetails, ArticleInstructionWithFullParentDTO.class);
                if (articleInstruction.getName().toUpperCase().contains(keyword.toUpperCase())) {
                    listArticle.add(articleInstruction);
                }
                listArticle = getAllChildren(articleDetails, listArticle, keyword);
            }
        }
        return listArticle;
    }


    @Override
    public ArticleInstructionWithParentIdDTO findByIdForInstruction(Long id) {
        Optional<Article> theArticle = articleRepository.findById(id);

        ArticleInstructionWithParentIdDTO articleJson = ObjectMapperUtils.map(theArticle.get(), ArticleInstructionWithParentIdDTO.class);

        if (theArticle.get().getParentArticle() != null) {
            articleJson.setParentId(theArticle.get().getParentArticle().getId());
        }

        return articleJson;

    }


    @Override
    public void save(ArticleRequestDTO articleRequest) {
        Article parentArticle = null;

        if (articleRequest.getParentId() != null) {
            parentArticle = new Article();
            parentArticle.setId(articleRequest.getParentId());
        }

        Article theArticle = new Article();
        theArticle.setId(articleRequest.getId());
        theArticle.setName(articleRequest.getName());
        theArticle.setRoot(articleRequest.isRoot());
        theArticle.setParentArticle(parentArticle);

        articleRepository.save(theArticle);
    }


    @Override
    public void delete(Long id) {
        Optional<Article> theArticle = articleRepository.findById(id);

        if (theArticle.isPresent()) {
            // Find all children of this article
            List<Article> articles = articleRepository.findByParentId(theArticle.get().getId());

            // Find all posts from these articles
            List<Long> articleIds = articles.stream()
                    .map(Article::getId)
                    .collect(Collectors.toList());
            articleIds.add(theArticle.get().getId());
            List<Post> posts = postRepository.findAllByArticleIds(articleIds);

            postRepository.deleteAll(posts);
            articleRepository.deleteAll(articles);
        }
    }
}
