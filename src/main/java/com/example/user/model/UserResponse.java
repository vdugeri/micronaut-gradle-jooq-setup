package com.example.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record UserResponse(
        String id,
        @JsonProperty(value = "first_name") String firstName,
        @JsonProperty(value = "last_name") String lastName,
        @JsonProperty(value = "created_at") OffsetDateTime createdAt) {}
