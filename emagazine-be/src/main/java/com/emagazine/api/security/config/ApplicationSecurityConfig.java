package com.emagazine.api.security.config;

import javax.crypto.SecretKey;

import com.emagazine.api.security.jwt.JwtConfig;
import com.emagazine.api.security.jwt.JwtTokenVerifier;
import com.emagazine.api.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.emagazine.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {
    private final AuthenticationConfiguration configuration;

    private final UserService userService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public ApplicationSecurityConfig(AuthenticationConfiguration configuration, UserService userService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig) {
        this.configuration = configuration;
        this.userService = userService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // stateless for jwt
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(configuration.getAuthenticationManager(),
                        jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authentication = new DaoAuthenticationProvider();

        authentication.setUserDetailsService(userService);            // set the custom user details service
        authentication.setPasswordEncoder(passwordEncoder());        // set the password encoder - bcrypt
        return authentication;
    }


}