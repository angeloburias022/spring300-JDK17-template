package com.template.spring300_jdk17.Authentication;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * Overridden method to filter each incoming HTTP request. It inspects the
     * Authorization header and logs information about the request. It then
     * delegates to the next filter in the chain.
     * 
     * @param request          the incoming HTTP request
     * @param response         the outgoing HTTP response
     * @param filterChain      the filter chain to continue with
     * @throws ServletException if an error occurs while processing the request
     * @throws IOException      if an error occurs while sending the response
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
    
        log.info("authorization header {}", authorizationHeader);        
        log.info("JwtRequestFilter: doFilterInternal {}", request.getRequestURI());
             
        filterChain.doFilter(request, response);
    }
    
}
