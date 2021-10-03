package com.fa.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fa.api.entity.User;

public interface UserService extends UserDetailsService {
	
	User findById(Long id);

	User findByUsername(String username);
}
