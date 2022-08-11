package com.emagazine.web.service.impl;

import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.emagazine.web.config.RestAPI;
import com.emagazine.web.model.ArticleDetails;
import com.emagazine.web.model.ArticleInstruction;
import com.emagazine.web.model.ArticleInstructionWithFullParent;
import com.emagazine.web.model.request.ArticleRequest;
import com.emagazine.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ArticleDetails> fetchMainArticles() {
        String url = RestAPI.URL + "/articles/heads";

        try {
            ResponseEntity<ArticleDetails[]> result = restTemplate.getForEntity(url, ArticleDetails[].class);

            // Modify a list, backed by the original array
            List<ArticleDetails> mainArticles =
                    new ArrayList<ArticleDetails>(Arrays.asList(result.getBody()));
            return mainArticles;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<ArticleInstruction> fetchSimpleMainArticle() {
        String url = RestAPI.URL + "/articles/heads/instructions";

        ResponseEntity<ArticleInstruction[]> result = restTemplate.getForEntity(url, ArticleInstruction[].class);

        // Modify a list, backed by the original array
        List<ArticleInstruction> mainArticles = Arrays.asList(Objects.requireNonNull(result.getBody()));
        return mainArticles;
    }


    @Override
    public ArticleDetails fetchArticle(Long id) {
        String url = RestAPI.URL + "/articles/" + id;

        ResponseEntity<ArticleDetails> result = restTemplate.getForEntity(url, ArticleDetails.class);

        ArticleDetails theArticle = result.getBody();

        return theArticle;
    }


    @Override
    public List<ArticleInstructionWithFullParent> fetchArticles() {
        String url = RestAPI.URL + "/articles/";

        ResponseEntity<ArticleInstructionWithFullParent[]> result =
                restTemplate.getForEntity(url, ArticleInstructionWithFullParent[].class);

        List<ArticleInstructionWithFullParent> articles =
                new ArrayList<>(Arrays.asList(result.getBody()));
        return articles;
    }


    @Override
    public List<ArticleInstructionWithFullParent> fetchAllChildsByArticleId(
            Long articleId,
            String keyword,
            HttpSession session) {

        String url = RestAPI.URL + "/articles/child/" + articleId;

        UriComponentsBuilder builderUri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("keyword", keyword);

        // Build URI
        URI uri = builderUri.build().encode().toUri();

        // Send JWT token in header
        HttpHeaders headers = new HttpHeaders();
        String token = (String) session.getAttribute("authorization");
        headers.add(HttpHeaders.AUTHORIZATION, token);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ArticleInstructionWithFullParent[]> result =
                restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
                        ArticleInstructionWithFullParent[].class);

        List<ArticleInstructionWithFullParent> childArticles =
                new ArrayList<>(Arrays.asList(result.getBody()));
        return childArticles;
    }


//	@Override
//	public ArticleInstructionWithFullParent fetchSimpleArticleWithParent(Long id) {
//		String url = RestAPI.URL + "/articles/" + id;
//
//		ResponseEntity<ArticleInstructionWithFullParent> result = 
//				restTemplate.getForEntity(url, ArticleInstructionWithFullParent.class);
//
//		ArticleInstructionWithFullParent theArticle = result.getBody();
//
//		return theArticle;
//	}
//	
//	
//	@Override
//	public ArticleRequest fetchArticleForRequest(Long id) {
//		String url = RestAPI.URL + "/articles/" + id + "/detail";
//
//		ResponseEntity<ArticleRequest> result = restTemplate.getForEntity(url, ArticleRequest.class);
//
//		ArticleRequest theArticle = result.getBody();
//
//		return theArticle;
//	}


    @Override
    public boolean deleteById(Long id, HttpSession session) {
        String url = RestAPI.URL + "/articles/" + id;

        // Send JWT token in header
        HttpHeaders header = new HttpHeaders();
        String token = (String) session.getAttribute("authorization");
        header.add(HttpHeaders.AUTHORIZATION, token);

        HttpEntity<?> requestEntity = new HttpEntity<>(header);

        // Call API
        ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, HashMap.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }


    @Override
    public boolean saveOrUpdate(ArticleRequest theArticle, HttpSession session) {
        String url = RestAPI.URL + "/articles/";

        // Send JWT token in header
        HttpHeaders header = new HttpHeaders();
        String token = (String) session.getAttribute("authorization");
        header.add(HttpHeaders.AUTHORIZATION, token);

        HttpMethod httpMethod = theArticle.getId() == null ? HttpMethod.POST : HttpMethod.PUT;

        HttpEntity<ArticleRequest> requestEntity = new HttpEntity<ArticleRequest>(theArticle, header);

        // Call API
        ResponseEntity<ArticleRequest> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, ArticleRequest.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;

    }
}
