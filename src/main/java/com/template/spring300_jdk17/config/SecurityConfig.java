package com.template.spring300_jdk17.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.template.spring300_jdk17.Authentication.JwtRequestFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String[] WHITE_LIST = { "/v3/api-docs/**", "/swagger-ui/**"};
    
    /**
     * Security configuration for Spring Security. Disables CSRF protection, allows access to OpenAPI
     * and Swagger UI, secures other endpoints with HTTP Basic Auth, and disables form-based login.
     * 
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if there is an error configuring the SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                WHITE_LIST
                        ).permitAll() // Allow access to OpenAPI and Swagger UI`
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);       
        return http.build();
    }

}
