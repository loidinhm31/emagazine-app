package com.emagazine.web.model;

import java.util.Date;

public class CommentForListView {
    private Long id;
    private String Username;
    private String UserEmail;
    private Date dateCreate;
    private PostSimple post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public PostSimple getPost() {
        return post;
    }

    public void setPost(PostSimple post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "CommentForListView [id=" + id + ", Username=" + Username + ", UserEmail=" + UserEmail + ", dateCreate="
                + dateCreate + ", post=" + post + "]";
    }


}
