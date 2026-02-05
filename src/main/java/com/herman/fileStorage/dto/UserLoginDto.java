package com.herman.fileStorage.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserLoginDto(
        String username,
        @JsonIgnore String password
) {}
