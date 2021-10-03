package com.fa.mockweb.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fa.mockweb.model.Post;
import com.fa.mockweb.model.PostInstruction;
import com.fa.mockweb.model.PostRequest;
import com.fa.mockweb.utils.RestPageHelper;



public interface PostService {
	List<PostInstruction> fetchPostsByArticleId(Long articleId);
	
	
	/**
	 * get top 5 post of each main article order 
	 * by the date it was created.
	 * @return /'Map: String, List of PostList'/
	 */
	Map<String, List<PostInstruction>> fetchHomePosts();
	
	List<PostInstruction> fetchTopPostsForReview(Long articleId);

	Post fetchPostsById(Long id);
		
	RestPageHelper<PostInstruction> fetchPostsByParentArticle(Long parentId, Pageable pageable);
	

	boolean saveOrUpdate(PostRequest post, HttpSession session);
	
	String uploadImage(MultipartFile multipartFile, HttpSession session);
	
	boolean deleteById(Long id, HttpSession session);
	
	List<PostInstruction> fetchPostByComment(HttpSession session);


	RestPageHelper<Post> fetchPostByArticleId(Long articleId, String keyword, Pageable pageable, 
			HttpSession session);

}
