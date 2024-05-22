package com.example.user.provider;

import com.example.tables.pojos.Users;
import com.example.user.model.UserRequest;
import com.example.user.repository.UserRepository;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class UserProvider {

    private final UserRepository userRepository;

    public UserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<Users> createUser(UserRequest userRecord) {
        return userRepository.createUser(userRecord);
    }
}
