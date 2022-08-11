package com.emagazine.web.model.request;

public class CommentRequest {
	
	private String username;
	
	private String userEmail;
	
	private String content;
	
	private Long postId;

	public CommentRequest() {

	}

	public CommentRequest(String username, String userEmail, String content, Long postId) {
		this.username = username;
		this.userEmail = userEmail;
		this.content = content;
		this.postId = postId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	
}
