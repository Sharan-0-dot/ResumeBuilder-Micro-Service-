package com.ResumeBuilder.Gateway.Service;

import com.ResumeBuilder.Gateway.Model.User;
import com.ResumeBuilder.Gateway.Repository.ReactiveUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ReactiveUserRepo repo;
    private final JWT_Service jwtService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Mono<User> postUser(User user) {
        user.setRoles(new ArrayList<>(List.of("USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public Mono<User> postAdmin(User user) {
        user.setRoles(new ArrayList<>(List.of("USER", "ADMIN")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public Mono<User> putUserReactive(User user, String username) {
        return repo.findByUsername(username)
                .flatMap(u -> {
                    u.setUsername(user.getUsername());
                    u.setPassword(passwordEncoder.encode(user.getPassword()));
                    return repo.save(u);
                });
    }

    public Mono<User> deleteUserReactive(User user) {
        return repo.findById(user.getObjectId())
                .flatMap(u -> repo.delete(u).then(Mono.just(u)));
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public Mono<User> getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
