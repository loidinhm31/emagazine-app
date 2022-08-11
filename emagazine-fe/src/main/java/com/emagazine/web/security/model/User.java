package com.emagazine.web.security.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private String name;
	
	private Set<Authority> authorities;
	
	public User() {
		
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Set<Authority> getAuthorities() {
		return authorities;
	}



	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", authorities=" + authorities + "]";
	}



	

	
	
	
	
}
