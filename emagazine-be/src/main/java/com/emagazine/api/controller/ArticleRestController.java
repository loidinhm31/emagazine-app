package com.emagazine.api.controller;

import java.util.List;

import com.emagazine.api.rest.exception.ObjectNotFoundException;
import com.emagazine.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emagazine.api.model.ArticleDetailsDTO;
import com.emagazine.api.model.ArticleInstructionDTO;
import com.emagazine.api.model.ArticleInstructionWithFullParentDTO;
import com.emagazine.api.model.ArticleInstructionWithParentIdDTO;
import com.emagazine.api.model.ArticleRequestDTO;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/heads")
    public List<ArticleDetailsDTO> getMainArticles() {

        List<ArticleDetailsDTO> articles = articleService.findMainArticles();

        return articles;
    }


    @GetMapping("heads/instructions")
    public List<ArticleInstructionDTO> getSimpleMainArtilces() {

        List<ArticleInstructionDTO> articles = articleService.findSimpleMainArticles();

        return articles;
    }


    @GetMapping("/instructions/{id}")
    public ArticleInstructionWithParentIdDTO getArticleInstructionWithParentId(@PathVariable Long id) {
        ArticleInstructionWithParentIdDTO theArticle = articleService.findByIdForInstruction(id);

        if (theArticle == null) {
            throw new ObjectNotFoundException("Not found article");
        }

        return theArticle;
    }


    @GetMapping("/{id}")
    public ArticleDetailsDTO getArticle(@PathVariable Long id) {
        ArticleDetailsDTO theArticle = articleService.findById(id);

        if (theArticle == null) {
            throw new ObjectNotFoundException("Not found article");
        }

        return theArticle;
    }


    @GetMapping
    public List<ArticleInstructionWithFullParentDTO> getAllArticles() {
        return articleService.findAllArticles();
    }


    @GetMapping("/child/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ArticleInstructionWithFullParentDTO> getChildArticle(
            @PathVariable Long id,
            @RequestParam(required = false) String keyword) {
        return articleService.findAllChildrenById(id, keyword);
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ArticleRequestDTO saveOrUpdateArticle(@RequestBody ArticleRequestDTO articleRequest) {

        articleService.save(articleRequest);

        return articleRequest;
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteArticle(@PathVariable(value = "id") Long id) {
        articleService.delete(id);
    }
}
