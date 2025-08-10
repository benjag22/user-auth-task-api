package com.example.auth_task_api.api.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        @JsonProperty("access_token")
        String accessToken
) {
}
