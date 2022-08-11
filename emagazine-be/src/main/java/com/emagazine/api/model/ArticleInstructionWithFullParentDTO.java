package com.emagazine.api.model;

public class ArticleInstructionWithFullParentDTO {
	private Long id;

	private String name;

	private boolean isRoot;
	
	private ArticleInstructionDTO parentArticle;

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

	public ArticleInstructionDTO getParentArticle() {
		return parentArticle;
	}

	public void setParentArticle(ArticleInstructionDTO parentArticle) {
		this.parentArticle = parentArticle;
	}

	
	
}
