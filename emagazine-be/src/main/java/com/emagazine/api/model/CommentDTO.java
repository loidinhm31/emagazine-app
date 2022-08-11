package com.emagazine.api.model;

import java.util.Date;

public class CommentDTO {

    private Long id;

    private String username;

    private String userEmail;

    private String content;

    private Date dateCreate;

    private PostDetailsDTO post;


    public CommentDTO() {

    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setPost(PostDetailsDTO post) {
        this.post = post;
    }

    public Long getPostId() {
        return post.getId();
    }


}
