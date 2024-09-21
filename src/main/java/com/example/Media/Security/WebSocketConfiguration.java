package com.example.Media.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
<<<<<<< HEAD
 * Configuration class for WebSocket communication using Spring's WebSocket support.
=======
 * Configuration class for WebSocket communication using Spring's WebSocket
 * support.
>>>>>>> master
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Register Stomp endpoints for WebSocket communication.
     *
<<<<<<< HEAD
     * @param registry The StompEndpointRegistry used to register WebSocket endpoints.
=======
     * @param registry The StompEndpointRegistry used to register WebSocket
     *                 endpoints.
>>>>>>> master
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /**
     * Configure message broker options for WebSocket communication.
     *
<<<<<<< HEAD
     * @param registry The MessageBrokerRegistry used to configure message broker options.
=======
     * @param registry The MessageBrokerRegistry used to configure message broker
     *                 options.
>>>>>>> master
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
