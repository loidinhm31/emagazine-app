package com.emagazine.web.model;

import java.util.List;
import java.util.Objects;

import com.emagazine.web.utils.Slugify;

public class ArticleDetails {

	private Long id;
	
	private String name;
	
	private boolean isRoot;
	
	private ArticleInstructionWithFullParent parentArticle;
	
	private List<ArticleDetails> childArticles;
	

	public ArticleDetails() {
		
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
		
	public String getCode() {
		return Slugify.convert(name);
	}

	
	
	public boolean isRoot() {
		return isRoot;
	}


	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public ArticleInstructionWithFullParent getParentArticle() {
		return parentArticle;
	}


	public void setParentArticle(ArticleInstructionWithFullParent parentArticle) {
		this.parentArticle = parentArticle;
	}


	public List<ArticleDetails> getChildArticles() {
		return childArticles;
	}

	public void setChildArticles(List<ArticleDetails> childArticles) {
		this.childArticles = childArticles;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ArticleDetails Article = (ArticleDetails) obj;
        return this.getId().equals(Article.getId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    
    
    
    
}
