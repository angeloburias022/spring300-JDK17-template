package com.template.spring300_jdk17.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public WebSocketConfig() {
        log.info("[TRACE] Websocket config..");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("[TRACE] registerStompEndpoints");
        registry.addEndpoint("/ws").setAllowedOrigins("*")
                .addInterceptors(new CustomHandShakeInterceptor());
        // .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        log.info("[TRACE] configureMessageBroker ");
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
}
