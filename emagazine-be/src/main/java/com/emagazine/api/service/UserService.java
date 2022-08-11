package com.emagazine.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.emagazine.api.entity.User;

public interface UserService extends UserDetailsService {

    User findById(Long id);

    User findByUsername(String username);
}
