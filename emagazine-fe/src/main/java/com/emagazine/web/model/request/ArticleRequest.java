package com.emagazine.web.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class ArticleRequest {
	
	private Long id;
	
	@NotBlank(message="Please fill out this field")
	@Size(min = 8, max = 50, message = "Name of the article must have 8 to 50 characters")
	private String name;
	
	private boolean isRoot;
	
	private Long parentId;

	public ArticleRequest() {
		
	}
	
	
	
	public ArticleRequest(Long id, String name, boolean isRoot, Long parentId) {
		this.id = id;
		this.name = name;
		this.isRoot = isRoot;
		this.parentId = parentId;
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
