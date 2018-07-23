package de.tu_clausthal.in.bachelorproject2018.poker.network.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.stream.Stream;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public static final String TOPIC = "/topic";
    public static final String QUEUE = "/queue";
    public static final String MESSAGE = "/message";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker(TOPIC, QUEUE, MESSAGE );
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/poker").withSockJS();
    }

}
