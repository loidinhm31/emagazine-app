package com.emagazine.api.model;

import java.util.Date;

public class PostDetailsDTO {

    private Long id;

    private String title;

    private String thumbnail;

    private String shortDescription;

    private String content;

    private Date dateCreate;

    private Date dateUpdate;

    private ArticleInstructionDTO article;


    public PostDetailsDTO() {

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


    public Date getDateCreate() {
        return dateCreate;
    }


    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }


    public Date getDateUpdate() {
        return dateUpdate;
    }


    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }


    public ArticleInstructionDTO getArticle() {
        return article;
    }


    public void setArticle(ArticleInstructionDTO article) {
        this.article = article;
    }


}
