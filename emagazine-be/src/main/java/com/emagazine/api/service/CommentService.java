package com.emagazine.api.service;

import java.util.List;

import com.emagazine.api.model.CommentDTO;
import com.emagazine.api.model.CommentForListViewDTO;
import com.emagazine.api.model.CommentRequestDTO;

public interface CommentService {
	
	CommentDTO findById(Long id);
	
	List<CommentDTO> findByPostId(Long postId);
	
	void save(CommentRequestDTO commentDTO);
	
	List<CommentForListViewDTO> findAll(String keyword);
	
	void delete(Long id);

	List<CommentForListViewDTO> findByPostIdForAdmin(Long postId, String keyword);
}
