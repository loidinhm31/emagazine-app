package com.emagazine.api.repository;

import java.util.List;

import com.emagazine.api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByContentContainingIgnoreCaseOrderByDateCreateDesc(String content);

    List<Comment> findByPostIdOrderByDateCreate(Long postId);

    List<Comment> findFirst50ByOrderByDateCreateDesc();

    List<Comment> findByPostIdAndContentContainingIgnoreCaseOrderByDateCreateDesc(Long postId, String keyword);

}
