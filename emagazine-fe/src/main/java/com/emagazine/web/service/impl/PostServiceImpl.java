package com.emagazine.web.service.impl;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.emagazine.web.config.RestAPI;
import com.emagazine.web.model.Post;
import com.emagazine.web.model.PostInstruction;
import com.emagazine.web.model.request.ArticleRequest;
import com.emagazine.web.model.request.PostRequest;
import com.emagazine.web.service.PostService;
import com.emagazine.web.utils.RestPageHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<PostInstruction> fetchPostsByArticleId(Long articleId) {
		String url = RestAPI.URL + "/posts";
		
		// Build parameter
		UriComponentsBuilder builderUri = UriComponentsBuilder
				.fromHttpUrl(url)
				.queryParam("article-id", articleId);
			

		// Build URI
		URI uri = builderUri.build().encode().toUri();

		try {
			ResponseEntity<PostInstruction[]> result = 
					restTemplate.getForEntity(uri, PostInstruction[].class);
			
			// Modify a list, backed by the original array
			List<PostInstruction> posts = 
					new ArrayList<>(Arrays.asList(result.getBody()));
			return posts;
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public Page<Post> fetchPostByArticleId(Long articleId, String keyword, Pageable pageable, HttpSession session) {
		
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		
		String url = RestAPI.URL + "/posts/details";
		
		// Build parameter
		UriComponentsBuilder builderUri = UriComponentsBuilder
				.fromHttpUrl(url)
				.queryParam("article-id", articleId)
				.queryParam("page", currentPage)
				.queryParam("size", pageSize);
		
		// Send JWT token in header
		HttpHeaders headers = new HttpHeaders();
		String token = (String) session.getAttribute("authorization");
		headers.add(HttpHeaders.AUTHORIZATION, token);
		
		HttpEntity<Post> requestEntity = new HttpEntity<>(headers);		
		
		// Set default value for keyword
		if(keyword == null) {
			keyword = "";
		}	
		builderUri.queryParam("keyword", keyword);
		
		// Build URI
		URI uri = builderUri.build().encode().toUri();
	
		ParameterizedTypeReference<RestPageHelper<Post>> responseType =
				new ParameterizedTypeReference<>() {};
		
		try {
			ResponseEntity<RestPageHelper<Post>> result =
					restTemplate.exchange(uri, HttpMethod.GET, requestEntity, responseType);

			RestPageHelper<Post> posts = result.getBody();
			return posts;
		} catch (HttpClientErrorException e) {
			return null;
		}
	}
	

	@Override
	public Map<String, List<PostInstruction>> fetchHomePosts() {
		String uri = RestAPI.URL + "/posts/top";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ParameterizedTypeReference<LinkedHashMap<String, List<PostInstruction>>> responseType = 
				new ParameterizedTypeReference<LinkedHashMap<String, List<PostInstruction>>>() {};
		
		try {
			ResponseEntity<LinkedHashMap<String, List<PostInstruction>>> result = restTemplate.exchange(uri, HttpMethod.GET,
					entity, responseType);
			
			return result.getBody();
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	@Override
	public List<PostInstruction> fetchTopPostsForReview(Long articleId) {

		String uri = RestAPI.URL + "/posts/reviews/" + articleId;
		
		try {
			ResponseEntity<PostInstruction[]> result = restTemplate.getForEntity(uri, PostInstruction[].class);

			List<PostInstruction> posts = Arrays.asList(result.getBody());
			return posts;
		} catch(HttpClientErrorException e) {	
			return null;
		}		
	}

	
	@Override
	public Post fetchPostsById(Long id) {
		String uri = RestAPI.URL + "/posts/" + id;
		
		try {
			ResponseEntity<Post> result = restTemplate.getForEntity(uri, Post.class);

			Post post = result.getBody();			
			return post;
		} catch (HttpClientErrorException e) {			
			return null;
		}
	}

	
	@Override
	public Page<PostInstruction> fetchPostsByParentArticle(Long parentId, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();

		String url = RestAPI.URL + "/posts/parent";
		
		// Build parameter
		UriComponentsBuilder builderUri = UriComponentsBuilder
				.fromHttpUrl(url)
				.queryParam("parent-id", parentId)
				.queryParam("page", currentPage)
				.queryParam("size", pageSize);
		
		// Build URI
		URI uri = builderUri.build().encode().toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<RestPageHelper<PostInstruction>> responseType =
				new ParameterizedTypeReference<>() {};
	
		try {
			ResponseEntity<RestPageHelper<PostInstruction>> result =
					restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);

			RestPageHelper<PostInstruction> posts = result.getBody();
			return posts;
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	
	@Override
	public boolean saveOrUpdate(PostRequest thePost, HttpSession session) {
		String uri = RestAPI.URL + "/posts";
		
		// Send JWT token in header
		HttpHeaders headers = new HttpHeaders();
		String token = (String) session.getAttribute("authorization");
		headers.add(HttpHeaders.AUTHORIZATION, token);
	
		HttpMethod httpMethod = thePost.getId() == null ? HttpMethod.POST : HttpMethod.PUT;
		
		HttpEntity<PostRequest> requestEntity = new HttpEntity<>(thePost, headers);
		
		// Call API
		ResponseEntity<PostRequest> responseEntity = restTemplate.exchange(uri, httpMethod, requestEntity, PostRequest.class);
	
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public String uploadImage(MultipartFile multipartFile, HttpSession session) {
		
		if (multipartFile == null) return null;
		
		String url = RestAPI.URL + "/posts/thumbnails/upload";	
		
		String token = (String) session.getAttribute("authorization");
		
		// Send JWT token in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add(HttpHeaders.AUTHORIZATION, token);
				
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		try {
			body.add("thumbnail", parseFile(multipartFile));
		} catch (IOException | MaxUploadSizeExceededException e) {

			return null;
		}

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

		return response.getStatusCode() == HttpStatus.CREATED ? response.getBody() : null;
	}
	
	private Resource parseFile(MultipartFile multipartFile) throws IOException {
		StringBuilder fileName = 
				new StringBuilder(String.valueOf(FilenameUtils.getName(multipartFile.getOriginalFilename()).hashCode()))
					.append(".")
					.append(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
		
		Path file = Files.createTempFile(System.currentTimeMillis() + "_" , fileName.toString());
		Files.write(file, multipartFile.getBytes());
		
		return new FileSystemResource(file.toFile());
	}

	
	@Override
	public List<PostInstruction> fetchPostByComment(HttpSession session) {
		String url = RestAPI.URL + "/posts/comments/censor";
		
		try {
			
			
			// Send JWT token in header
			String token = (String) session.getAttribute("authorization");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, token);
			
			HttpEntity<PostInstruction[]> requestEntity = new HttpEntity<>(headers);
			
			ResponseEntity<PostInstruction[]> result = 
					restTemplate.exchange(url, HttpMethod.GET, requestEntity, PostInstruction[].class);
			
			List<PostInstruction> posts = Arrays.asList(Objects.requireNonNull(result.getBody()));
			
			return posts;
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@Override
	public boolean deleteById(Long id, HttpSession session) {
		String url = RestAPI.URL + "/posts/" + id;
		
		// Send JWT token in header
		HttpHeaders header = new HttpHeaders();
		String token = (String) session.getAttribute("authorization");
		header.add(HttpHeaders.AUTHORIZATION, token);
		
		HttpEntity<ArticleRequest> requestEntity = new HttpEntity<>(header);
		
		// Call API
		ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, HashMap.class);
		
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}		
		return false;
	}

}
