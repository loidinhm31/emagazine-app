package com.fa.api.model;

public class ArticleInstructionDTO {
	private Long id;
	
	private String name;
	
	private boolean isRoot;
	

	public ArticleInstructionDTO() {

	}

	public ArticleInstructionDTO(Long id, String name, boolean isRoot) {
		this.id = id;
		this.name = name;
		this.isRoot = isRoot;
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
	
	
	
}
