package com.fa.api.model;

import java.util.Date;

public class CommentForListViewDTO {
	private Long id;
	
	private String username;
	
	private String userEmail;
	
	private Date dateCreate;
	
	private PostInstructionDTO post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public PostInstructionDTO getPost() {
		return post;
	}

	public void setPost(PostInstructionDTO post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "CommentForListViewDTO [id=" + id + ", username=" + username + ", userEmail=" + userEmail
				+ ", dateCreate=" + dateCreate + ", post=" + post + "]";
	}
	
	
}
