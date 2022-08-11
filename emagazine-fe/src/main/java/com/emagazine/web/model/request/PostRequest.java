package com.emagazine.web.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostRequest {

    private Long id;

    @NotBlank(message = "Please fill out this field")
    private String title;

    private String thumbnail;

    @Size(min = 8, max = 50, message = "Short description must have 8 to 50 characters")
    private String shortDescription;

    @NotBlank(message = "Please fill out this field")
    @Size(min = 50, message = "Content is too short")
    private String content;

    @NotNull
    private Long articleId;


    public PostRequest() {

    }

    public PostRequest(Long id, String title, String thumbnail, String shortDescription, String content,
                       Long articleId) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.shortDescription = shortDescription;
        this.content = content;
        this.articleId = articleId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "PostRequest [id=" + id + ", title=" + title + ", thumbnail=" + thumbnail + ", shortDescription="
                + shortDescription + ", content=" + content + ", articleId=" + articleId + "]";
    }


}
