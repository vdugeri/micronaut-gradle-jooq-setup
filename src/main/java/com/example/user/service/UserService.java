package com.example.user.service;

import com.example.tables.pojos.Users;
import com.example.user.model.UserRequest;
import com.example.user.model.UserResponse;
import com.example.user.provider.UserProvider;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class UserService {

  private final UserProvider userProvider;

  public UserService(UserProvider userProvider) {
    this.userProvider = userProvider;
  }

  public Mono<Users> createUser(UserRequest userRecord) {
    return userProvider.createUser(userRecord);
  }
}
