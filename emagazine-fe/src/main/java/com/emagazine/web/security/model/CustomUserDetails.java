package com.emagazine.web.security.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;


    public CustomUserDetails(User theUser) {
        this.user = theUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Authority> roles = user.getAuthorities();

        return roles.stream().map(
                role -> new SimpleGrantedAuthority(
                        role.getAuthority())).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {

        return null;
    }

    @Override
    public String getUsername() {

        return user.getName();
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