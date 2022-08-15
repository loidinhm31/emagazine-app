package com.emagazine.web.service.impl;


import java.io.IOException;

import com.emagazine.web.config.RestAPI;
import com.emagazine.web.model.request.LoginRequest;
import com.emagazine.web.security.model.CustomUserDetails;
import com.emagazine.web.security.model.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emagazine.web.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String excecuteLogin(LoginRequest loginRequest) {
        String url = RestAPI.URL + "/login";

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, header);

        ResponseEntity<?> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url,
                    HttpMethod.POST, entity, LoginRequest.class);

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Cannot found username", e);
        }


        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {

            String authorizationHeader = responseEntity.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String token = authorizationHeader.replace("Bearer", "");

            String decodeString = decodeToken(token);

            boolean isVerify = verifyUser(decodeString);

            if (isVerify) {
                return authorizationHeader;
            }
        }

        return null;
    }


    private String decodeToken(String token) {

        String[] tokenSplit = token.split("\\.");
        String tokenPayload = tokenSplit[1];

        Base64 base64 = new Base64(true);
        String decodePayload = new String(base64.decode(tokenPayload.getBytes()));

        return decodePayload;
    }

    private boolean verifyUser(String ObjectJson) {
        try {
            User user = new ObjectMapper().readValue(ObjectJson, User.class);

            UserDetails userDetails = new CustomUserDetails(user);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
