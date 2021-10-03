package com.fa.api.security.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fa.api.entity.Role;
import com.fa.api.entity.User;

public class CustomUserDetail implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public CustomUserDetail(User theUser) {
		this.user =  theUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Role> userRoles = user.getRoles();
		
		return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
	}

	@Override
	public String getPassword() {
		
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
