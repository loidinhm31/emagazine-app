package com.fa.api.service;

import java.util.List;

import com.fa.api.model.CommentDTO;
import com.fa.api.model.CommentForListViewDTO;
import com.fa.api.model.CommentRequestDTO;

public interface CommentService {
	
	CommentDTO findById(Long id);
	
	List<CommentDTO> findByPostId(Long postId);
	
	void save(CommentRequestDTO commentDTO);
	
	List<CommentForListViewDTO> findAll(String keyword);
	
	void delete(Long id);

	List<CommentForListViewDTO> findByPostIdForAdmin(Long postId, String keyword);
}
