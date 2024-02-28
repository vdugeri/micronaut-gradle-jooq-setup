package com.example.user.controller;

import com.example.user.model.UserRequest;
import com.example.user.model.UserResponse;
import com.example.user.service.UserService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Controller(value = "users")
@Tag(name = "users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Post
  @ApiResponse(description = "create a new user", responseCode = "200",
               content = @Content(schema = @Schema(implementation = UserResponse.class)))
  public Mono<UserResponse> createUser(@Body UserRequest request) {
    return userService.createUser(request)
                      .flatMap(record -> Mono.just(new UserResponse(
                        record.getId().toString(),
                        record.getFirstName(),
                        record.getLastName(),
                        record.getCreatedAt()
                      )));
  }
}
