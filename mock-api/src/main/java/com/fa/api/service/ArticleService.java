package com.fa.api.service;

import java.util.List;

import com.fa.api.model.ArticleDetailsDTO;
import com.fa.api.model.ArticleInstructionWithFullParentDTO;
import com.fa.api.model.ArticleInstructionDTO;
import com.fa.api.model.ArticleInstructionWithParentIdDTO;
import com.fa.api.model.ArticleRequestDTO;

public interface ArticleService {
	
	List<ArticleDetailsDTO> findMainArticles();
	
	ArticleDetailsDTO findById(Long id);	
	
	List<ArticleInstructionWithFullParentDTO> findAllArticles();

	void save(ArticleRequestDTO articleRequest);

	List<ArticleInstructionDTO> findSimpleMainArticles();

	ArticleInstructionWithParentIdDTO findByIdForInstruction(Long id);
	
	List<ArticleInstructionWithFullParentDTO> findAllChildsById(Long id, String keyword);
	
	void delete(Long id);
	

}
