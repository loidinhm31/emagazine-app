package com.emagazine.api.model;

import java.util.List;

public class ArticleDetailsDTO {

    private Long id;

    private String name;

    private boolean isRoot;

    private ArticleDetailsDTO parentArticle;

    private List<ArticleDetailsDTO> childArticles;


    public ArticleDetailsDTO() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public List<ArticleDetailsDTO> getChildArticles() {
        return childArticles;
    }

    public void setChildArticles(List<ArticleDetailsDTO> childArticles) {
        this.childArticles = childArticles;
    }

    public ArticleInstructionDTO getParentArticle() {
        if (parentArticle != null) {
            return new ArticleInstructionDTO(parentArticle.getId(), parentArticle.getName(),
                    parentArticle.isRoot());
        }

        return null;
    }

    public void setParentArticle(ArticleDetailsDTO parentArticle) {
        this.parentArticle = parentArticle;
    }


}
