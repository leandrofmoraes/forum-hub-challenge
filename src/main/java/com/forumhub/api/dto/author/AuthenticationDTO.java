package com.forumhub.api.dto.author;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
    @NotNull String username,
    @NotNull @Length(min = 4) String password) {
}
