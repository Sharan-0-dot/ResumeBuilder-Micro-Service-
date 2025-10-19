package com.ResumeBuilder.Gateway.Repository;

import com.ResumeBuilder.Gateway.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveUserRepo extends ReactiveMongoRepository<User, ObjectId> {
    Mono<User> findByUsername(String username);
}
