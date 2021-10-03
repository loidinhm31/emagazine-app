package com.fa.api.model;

public class ArticleInstructionWithParentIdDTO {
	
	private Long id;

	private String name;

	private boolean isRoot;
	
	private Long parentId;

	public ArticleInstructionWithParentIdDTO() {
		
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
