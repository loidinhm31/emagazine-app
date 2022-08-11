package com.emagazine.api.service;

import java.util.List;

import com.emagazine.api.model.ArticleDetailsDTO;
import com.emagazine.api.model.ArticleInstructionWithFullParentDTO;
import com.emagazine.api.model.ArticleInstructionDTO;
import com.emagazine.api.model.ArticleInstructionWithParentIdDTO;
import com.emagazine.api.model.ArticleRequestDTO;

public interface ArticleService {

    List<ArticleDetailsDTO> findMainArticles();

    ArticleDetailsDTO findById(Long id);

    List<ArticleInstructionWithFullParentDTO> findAllArticles();

    void save(ArticleRequestDTO articleRequest);

    List<ArticleInstructionDTO> findSimpleMainArticles();

    ArticleInstructionWithParentIdDTO findByIdForInstruction(Long id);

    List<ArticleInstructionWithFullParentDTO> findAllChildrenById(Long id, String keyword);

    void delete(Long id);


}
