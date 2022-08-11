package com.emagazine.web.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.emagazine.web.config.RestAPI;
import com.emagazine.web.model.Comment;
import com.emagazine.web.model.CommentForListView;
import com.emagazine.web.model.request.CommentRequest;
import com.emagazine.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void saveComment(CommentRequest theComment) {
        String url = RestAPI.URL + "/comments";

        // Create header
        HttpHeaders header = new HttpHeaders();
        header.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<CommentRequest> requestEntity = new HttpEntity<CommentRequest>(theComment, header);

        // Call API
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, CommentRequest.class);

    }

    @Override
    public List<Comment> fetchCommentsForPost(Long postId) {
        String url = RestAPI.URL + "/comments/" + postId + "/post";

        try {
            // Call API
            ResponseEntity<Comment[]> result = restTemplate.getForEntity(url, Comment[].class);
            List<Comment> comments = Arrays.asList(result.getBody());
            return comments;
        } catch (HttpClientErrorException e) {
            return null;
        }

    }

    @Override
    public List<CommentForListView> findAll(String keyword, HttpSession session) {
        String url = RestAPI.URL + "/comments";

        // Build parameter
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

        try {
            // Call API
            ResponseEntity<CommentForListView[]> result =
                    restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
                            CommentForListView[].class);
            List<CommentForListView> comments = Arrays.asList(result.getBody());

            return comments;
        } catch (HttpClientErrorException e) {
            return null;
        }
    }


    @Override
    public boolean deleteById(Long id, HttpSession session) {
        String url = RestAPI.URL + "/comments/" + id;

        // Build parameter
        UriComponentsBuilder builderUri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("id", id);

        // Build URI
        URI uri = builderUri.build().encode().toUri();

        // Send JWT token in header
        HttpHeaders headers = new HttpHeaders();
        String token = (String) session.getAttribute("authorization");
        headers.add(HttpHeaders.AUTHORIZATION, token);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // Call API
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, String.class);

        if (result.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }


    @Override
    public Comment findById(Long id) {
        String url = RestAPI.URL + "/comments/" + id;

        ResponseEntity<Comment> result = restTemplate.getForEntity(url, Comment.class);

        Comment theComment = result.getBody();

        return theComment;
    }

    @Override
    public List<CommentForListView> findByPostIdForAdminPage(Long id, String keyword,
                                                             HttpSession session) {
        String url = RestAPI.URL + "/comments/" + id + "/post/admin";

        // Build parameter
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

        try {
            // Call API
            ResponseEntity<CommentForListView[]> result =
                    restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
                            CommentForListView[].class);

            List<CommentForListView> comments = Arrays.asList(result.getBody());
            return comments;
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

}
