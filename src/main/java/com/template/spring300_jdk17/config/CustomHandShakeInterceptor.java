package com.template.spring300_jdk17.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomHandShakeInterceptor implements HandshakeInterceptor {

    public CustomHandShakeInterceptor() {
        log.info("[TRACE] initializing handshake interceptor..");
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {

        // Log the request headers
        log.info("Handshake Request Headers: " + request.getHeaders());

        // Extract the token if it's present in the query parameters
        String token = request.getURI().getQuery(); // Gets the entire query string
        if (token != null) {
            String[] params = token.split("&");
            for (String param : params) {
                if (param.startsWith("token=")) {
                    log.info("Token: " + param.substring(6)); // Log the token value
                }
            }
        }

        return true; // Allow the handshake to proceed
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception exception) {
        // TODO Auto-generated method stub
        log.info("[TRACE] AAFTER HANDSHAKE");
    }
}
