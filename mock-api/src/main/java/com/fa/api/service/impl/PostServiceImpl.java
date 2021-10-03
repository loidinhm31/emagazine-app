package com.fa.api.service.impl;

import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fa.api.entity.Article;
import com.fa.api.entity.Comment;
import com.fa.api.entity.Post;
import com.fa.api.model.PostDetailsDTO;
import com.fa.api.model.PostInstructionDTO;
import com.fa.api.model.PostRequestDTO;
import com.fa.api.repository.ArticleRepository;
import com.fa.api.repository.CommentRepository;
import com.fa.api.repository.PostRepository;
import com.fa.api.service.PostService;
import com.fa.api.utils.ObjectMapperUtils;
import com.fa.api.utils.RestPageHelper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public PostDetailsDTO findById(Long id) {
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			PostDetailsDTO postPOJO = ObjectMapperUtils.map(post.get(), PostDetailsDTO.class);

			return postPOJO;
		}
		return null;
	}

	
	@Override
	public List<PostDetailsDTO> findByArticleId(Long articleId) {
		List<Post> posts = postRepository.findByArticleId(articleId);

		if (!posts.isEmpty()) {
			List<PostDetailsDTO> postJsons = ObjectMapperUtils.mapAll(posts, PostDetailsDTO.class);

			return postJsons;
		}
		return null;
	}

	
	@Override
	public Page<PostDetailsDTO> findAllPostsIncludeChildsByArticleId(Long articleId, 
			 int page, int size, String keyword) {
		// Set default value for keyword
		if(keyword == null) {
			keyword = "";
		}
		
		page = page <= 1 ? 0 : page - 1;
		
		Article theArticle = articleRepository.findById(articleId).orElseThrow();
		
		List<Long> idOfArticles = new ArrayList<>();
		idOfArticles = getIdOfChildArticles(idOfArticles, theArticle);
		idOfArticles.add(theArticle.getId());
		for (Long id : idOfArticles) {
			System.out.println(id);
		}
		
		// Get pageable from repository
		Page<Post> posts = 
			postRepository.findByArticleIdInAndMultipleConditions(idOfArticles, 
					keyword, PageRequest.of(page, size));
		
		List<PostDetailsDTO> postJsons = new ArrayList<>();
		if (posts != null && posts.hasContent()) {
			postJsons = ObjectMapperUtils.mapAll(posts.getContent(), PostDetailsDTO.class);
			
			System.out.println("size: " + postJsons.size());
			
			// Convert page for request by page helper
			Page<PostDetailsDTO> postPage = new RestPageHelper<PostDetailsDTO>(postJsons, posts.getPageable(),
					posts.getTotalElements());

			return postPage;
		}
		
		return null;
	}
	
	
	@Override
	public Map<String, List<PostInstructionDTO>> findTopPostOfAllChildArticles() {
		Map<String, List<PostInstructionDTO>> map = new LinkedHashMap<String, List<PostInstructionDTO>>();

		// Loop top article with id 1-4
		for (int i = 1; i < 5; i++) {
			List<Long> listArticleId = new ArrayList<Long>();
			List<PostInstructionDTO> postInstructions;

			Optional<Article> theArticle = articleRepository.findById(Long.valueOf(i));

			if (theArticle.isPresent()) {

				// add comment
				listArticleId = getIdOfChildArticles(listArticleId, theArticle.get());

				List<Post> listPost = postRepository.findTop5ByArticleIdInOrderByDateCreateDesc(listArticleId);

				postInstructions = ObjectMapperUtils.mapAll(listPost, PostInstructionDTO.class);

				map.put(theArticle.get().getName(), postInstructions);
			}

		}

		return map;
	}

	private List<Long> getIdOfChildArticles(List<Long> idOfChildArticles, Article theArticle) {
		try {
			List<Article> childArticles = theArticle.getChildArticles();
			for (Article child : childArticles) {
				idOfChildArticles.add(child.getId());

				idOfChildArticles = getIdOfChildArticles(idOfChildArticles, child);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idOfChildArticles;
	}

	
	@Override
	public List<PostInstructionDTO> findTop3PostsOfEachChildByParentArticleId(Long articleId) {

		// Get current article from repository
		Optional<Article> currArticle = articleRepository.findById(articleId);

		if (currArticle.isPresent()) {
			List<Article> childArticles = currArticle.get().getChildArticles();

			List<Post> posts = new ArrayList<>();

			for (Article child : childArticles) {
				List<Post> tempPosts = postRepository.findFirst3ByArticleIdOrderByDateCreate(child.getId());

				posts.addAll(tempPosts);
			}

			if (!posts.isEmpty()) {
				List<PostInstructionDTO> postJsons = ObjectMapperUtils.mapAll(posts, PostInstructionDTO.class);
				return postJsons;
			}

		}

		return null;
	}

	
	@Override
	public Page<PostDetailsDTO> findByParentArticleId(Long articleId, int page, int size) {

		page = page <= 1 ? 0 : page - 1;

		Optional<Article> parentArticle = articleRepository.findById(articleId);

		if (parentArticle.isPresent()) {
			List<Long> idOfChildArticles = new ArrayList<>();

			for (Article child : parentArticle.get().getChildArticles()) {

				idOfChildArticles.add(child.getId());
			}

			// Get pageable from repository
			Page<Post> posts = postRepository.findByArticleIdIn(idOfChildArticles, PageRequest.of(page, size));

			List<PostDetailsDTO> postJsons = new ArrayList<>();
			if (posts != null && posts.hasContent()) {
				postJsons = ObjectMapperUtils.mapAll(posts.getContent(), PostDetailsDTO.class);

				// Convert page for request by page helper
				Page<PostDetailsDTO> postPage = new RestPageHelper<PostDetailsDTO>(postJsons, posts.getPageable(),
						posts.getTotalElements());

				return postPage;
			}
		}
		return null;
	}

	
	@Override
	public void save(PostRequestDTO postRequest) {

		Optional<Article> theArticle = articleRepository.findById(postRequest.getArticleId());

		Post thePost = new Post();
		thePost.setId(postRequest.getId());
		thePost.setTitle(postRequest.getTitle());
		thePost.setThumbnail(postRequest.getThumbnail());
		thePost.setShortDescription(postRequest.getShortDescription());
		thePost.setContent(postRequest.getContent());
		thePost.setArticle(theArticle.get());

		postRepository.save(thePost);

	}

	
	@Override
	public String uploadImageFile(MultipartFile multipartFile) {
		if (multipartFile.getOriginalFilename().length() > 0 && !multipartFile.isEmpty()) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			Path uploadPath = FileSystems.getDefault().getPath("cdn", "thumbnails",
					String.valueOf(calendar.get(Calendar.YEAR)));

			try {

				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}

				InputStream inputStream = multipartFile.getInputStream();
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

				return filePath.toString();

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Cannot upload file");
			}
		}

		return null;
	}

	
	@Override
	public List<PostInstructionDTO> findTopPostByComment() {
		List<Comment> comments = commentRepository.findFirst50ByOrderByDateCreateDesc();
		List<Long> postIds = new ArrayList<Long>();
		
		for (Comment comment : comments) {
			postIds.add(comment.getPost().getId());
		}
		
		List<Post> posts = postRepository.findTop10ByIdIn(postIds);
		
		List<PostInstructionDTO> postJsons = ObjectMapperUtils.mapAll(posts, PostInstructionDTO.class);
		return postJsons;
	}

	@Override
	public List<PostDetailsDTO> findByDateCreateBetween(Date startDate, Date endDate) {
		List<Post> posts = postRepository.findByDateCreateGreaterThanEqualAndDateCreateLessThanEqual(startDate, endDate);
		
		if (!posts.isEmpty()) {
			List<PostDetailsDTO> postJsons = ObjectMapperUtils.mapAll(posts, PostDetailsDTO.class);

			return postJsons;
		}

		return null;
	}
	
	@Override
	public void delete(Long id) {
		
		Optional<Post> thePost = postRepository.findById(id);
		
		if (thePost.isPresent()) {
			postRepository.deleteById(id);
		}

		
	}	

}
