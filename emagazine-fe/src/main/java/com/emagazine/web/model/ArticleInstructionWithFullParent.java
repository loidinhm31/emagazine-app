package com.emagazine.web.model;

import com.emagazine.web.utils.Slugify;

public class ArticleInstructionWithFullParent {
	private Long id;
	
	private String name;
	
	private boolean isRoot;
	
	private ArticleInstructionWithFullParent parentArticle;
	
	public String getCode() {
		return Slugify.convert(name);
	}
	
	public ArticleInstructionWithFullParent() {
		
	}
	

	public ArticleInstructionWithFullParent(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public ArticleInstructionWithFullParent getParentArticle() {
		return parentArticle;
	}

	public void setParentArticle(ArticleInstructionWithFullParent parentArticle) {
		this.parentArticle = parentArticle;
	}

	
	
}
