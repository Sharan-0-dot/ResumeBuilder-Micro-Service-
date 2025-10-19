package com.Sharan.Resume_Service.Configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient aiServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://AI-SERVICE").build();
    }

    @Bean
    public WebClient downloadService(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://DOCUMENT-SERVICE").build();
    }
}
