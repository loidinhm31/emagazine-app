package com.emagazine.api.service.impl;

import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.emagazine.api.entity.Article;
import com.emagazine.api.entity.Comment;
import com.emagazine.api.entity.Post;
import com.emagazine.api.model.DataPieChart;
import com.emagazine.api.model.PostDetailsDTO;
import com.emagazine.api.model.PostInstructionDTO;
import com.emagazine.api.model.PostRequestDTO;
import com.emagazine.api.repository.ArticleRepository;
import com.emagazine.api.repository.CommentRepository;
import com.emagazine.api.repository.PostRepository;
import com.emagazine.api.service.PostService;
import com.emagazine.api.utils.ObjectMapperUtils;

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
		
		// Get pageable from repository
		Page<Post> posts = 
			postRepository.findByArticleIdInAndMultipleConditions(idOfArticles, 
					keyword, PageRequest.of(page, size));
		
		List<PostDetailsDTO> postJsons;
		if (posts != null && posts.hasContent()) {
			postJsons = ObjectMapperUtils.mapAll(posts.getContent(), PostDetailsDTO.class);

			// Convert page for request by page helper
			Page<PostDetailsDTO> postPage = new PageImpl<>(postJsons, posts.getPageable(),
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
				Page<PostDetailsDTO> postPage = new PageImpl<>(postJsons, posts.getPageable(),
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
	public List<DataPieChart> getDataPieChart(Date startDate,Date endDate) throws ParseException {
		
		
	
		List<Post> posts = postRepository.findByDateCreateGreaterThanEqualAndDateCreateLessThanEqual(startDate, endDate);
		
		if (!posts.isEmpty()) {
			List<PostDetailsDTO> postJsons = ObjectMapperUtils.mapAll(posts, PostDetailsDTO.class);

			Map<String,Integer> mapData = getDataForPieChart(postJsons);
			List<DataPieChart> listData = new ArrayList<DataPieChart>();
			for (Map.Entry<String, Integer> entry : mapData.entrySet()) {
				listData.add(new DataPieChart(entry.getKey(), entry.getValue()));
			}
			return listData;
		}

		return null;
	}
	private Map<String,Integer> getDataForPieChart(List<PostDetailsDTO> postJsons) {
		Map<String,Integer> mapData = new LinkedHashMap<String, Integer>();
		
		for (PostDetailsDTO post : postJsons) {
			String article = post.getArticle().getName();
			if(mapData.get(article) ==null) {
				mapData.put(article, 1);
			}else {
				mapData.put(article,mapData.get(article)+1);
				
			}
		}
		
		return mapData;
	}


	@Override
	public void delete(Long id) {
		
		Optional<Post> thePost = postRepository.findById(id);
		
		if (thePost.isPresent()) {
			postRepository.deleteById(id);
		}

		
	}

	@Override
	public Map<String, Integer> getColumnChartData() {
		Date endDate= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		Date startDate = cal.getTime();
		
		
		try {
			List<Post> posts = postRepository.findByDateCreateGreaterThanEqualAndDateCreateLessThanEqual(startDate, endDate);
			
			Map<String, Integer> map = new LinkedHashMap<String, Integer>();
			SimpleDateFormat formater = new SimpleDateFormat("MMM");
			for (Post post : posts) {
				Calendar temp = Calendar.getInstance();
				temp.setTimeInMillis(post.getDateCreate().getTime());
				if(map.get(formater.format(temp.getTime())) ==null) {
					map.put(formater.format(temp.getTime()), 1);
				}else {
					map.put(formater.format(temp.getTime()),map.get(formater.format(temp.getTime()))+1);
					
				}
			}
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public List<Date> getListMonth(){
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		for (int i = 0; i < 6; i++) {
			list.add( cal.getTime());
		}
		return list;
	}
	
	public boolean checkInMonth(Date givenDate,Date checkMonth) {
		//Create 2 instances of Calendar
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		//set the given date in one of the instance and current date in the other
		cal1.setTime(givenDate);
		cal2.setTime(checkMonth);

		//now compare the dates using methods on Calendar
		if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
		    if(cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
		        // the date falls in current month
		    	return true;
		    }
		}
		return false;
	}
	
	
}
