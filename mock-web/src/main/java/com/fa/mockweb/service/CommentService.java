package com.fa.mockweb.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fa.mockweb.model.Comment;
import com.fa.mockweb.model.CommentForListView;
import com.fa.mockweb.model.CommentRequest;

public interface CommentService {
	void saveComment(CommentRequest theComment);

	List<Comment> fetchCommentsForPost(Long postId);	
	
	List<CommentForListView> findAll(String keyword, HttpSession session);
	
	List<CommentForListView> findByPostIdForAdminPage(Long id, String keyword, HttpSession session);
	
	boolean deleteById(Long id, HttpSession session);
	
	Comment findById(Long id);
}
