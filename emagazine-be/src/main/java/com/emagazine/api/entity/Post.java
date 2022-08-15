package com.emagazine.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "NVARCHAR(50)")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "count_view")
    private Integer countView;

    @Column(name = "date_create", updatable = false, insertable = true)
    @CreationTimestamp
    private Date dateCreate;

    @Column(name = "date_update")
    @UpdateTimestamp
    private Date dateUpdate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
}