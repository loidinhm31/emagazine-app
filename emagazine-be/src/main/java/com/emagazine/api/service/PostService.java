package com.emagazine.api.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.emagazine.api.model.DataPieChart;
import com.emagazine.api.model.PostDetailsDTO;
import com.emagazine.api.model.PostInstructionDTO;
import com.emagazine.api.model.PostRequestDTO;

public interface PostService {

    PostDetailsDTO findById(Long id);

    List<PostDetailsDTO> findByArticleId(Long articleId);

    Map<String, List<PostInstructionDTO>> findTopPostOfAllChildArticles();

    List<PostInstructionDTO> findTop3PostsOfEachChildByParentArticleId(Long articleId);

    Page<PostDetailsDTO> findByParentArticleId(Long articleId, int page, int size);

    Page<PostDetailsDTO> findAllPostsIncludeChildsByArticleId(Long articleId, int page, int size, String keyword);

    void save(PostRequestDTO postRequest);

    String uploadImageFile(MultipartFile multipartFile);

    List<PostInstructionDTO> findTopPostByComment();

    List<DataPieChart> getDataPieChart(Date startDate, Date endDate) throws ParseException;

    void delete(Long id);

    Map<String, Integer> getColumnChartData();
}
