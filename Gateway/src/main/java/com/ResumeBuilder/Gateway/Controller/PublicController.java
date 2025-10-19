package com.ResumeBuilder.Gateway.Controller;

import com.ResumeBuilder.Gateway.Model.User;
import com.ResumeBuilder.Gateway.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    @PostMapping("/createUser/")
    public Mono<ResponseEntity<User>> createUser(@RequestBody User user) {
        return userService.postUser(user)
                .map(savedUser -> ResponseEntity.ok(savedUser))
                .doOnError(e -> log.error("Error creating user: {}", e.getMessage()))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @PostMapping("/login/")
    public Mono<ResponseEntity<String>> verify(@RequestBody User user) {
        return userService.getUserByUsername(user.getUsername())
                .flatMap(u -> {
                    if (userService.passwordMatches(user.getPassword(), u.getPassword())) {
                        String token = userService.generateToken(u.getUsername());
                        return Mono.just(ResponseEntity.ok(token));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found")))
                .doOnError(e -> log.error("Login error: {}", e.getMessage()));
    }
}
