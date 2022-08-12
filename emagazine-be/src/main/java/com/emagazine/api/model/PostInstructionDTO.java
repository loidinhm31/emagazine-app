package com.emagazine.api.model;

/**
 * Object post with shortcut data
 *
 * @author LeDH
 */
public class PostInstructionDTO {
    private Long id;

    private String title;

    private String thumbnail;

    private String shortDescription;

    private Integer countView;

    public PostInstructionDTO() {

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

    public Integer getCountView() {
        return countView;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }
}
