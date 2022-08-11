package com.emagazine.web.model;

import java.io.File;
import java.util.Date;

import com.emagazine.web.config.RestAPI;

public class Post {

	private Long id;

	private String title;

	private String thumbnail;

	private String shortDescription;

	private String content;
	
	private Date dateCreate;
	
	private Date dateUpdate;

	private ArticleInstructionWithFullParent article;


	public Post() {
	
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
		if (thumbnail != null && !thumbnail.startsWith("http")) {
			String URL = RestAPI.HOST + File.separator + thumbnail;
			return URL;
		}
			
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


	public ArticleInstructionWithFullParent getArticle() {
		return article;
	}


	public void setArticle(ArticleInstructionWithFullParent article) {
		this.article = article;
	}

	
}
