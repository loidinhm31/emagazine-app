package com.fa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.api.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
	List<Comment> findByContentContainingIgnoreCaseOrderByDateCreateDesc(String content);
	
	List<Comment> findByPostIdOrderByDateCreate(Long postId);
	
	List<Comment> findFirst50ByOrderByDateCreateDesc();
	
	List<Comment> findByPostIdAndContentContainingIgnoreCaseOrderByDateCreateDesc(Long postId, String keyword);

}
