package com.ResumeBuilder.Gateway.Configuration;

import com.ResumeBuilder.Gateway.Service.JWT_Service;
import com.ResumeBuilder.Gateway.Service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JWT_Filter implements WebFilter {

    private final JWT_Service jwtService;
    private final UserDetailsImpl userDetailsService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        String token;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        } else {
            token = null;
        }

        if (username != null) {
            return userDetailsService.findByUsername(username)
                    .flatMap(userDetails -> {
                        if (jwtService.validateToken(token, userDetails)) {
                            Authentication auth = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            return chain.filter(exchange)
                                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
                        } else {
                            return chain.filter(exchange);
                        }
                    });
        }

        return chain.filter(exchange);
    }
}
