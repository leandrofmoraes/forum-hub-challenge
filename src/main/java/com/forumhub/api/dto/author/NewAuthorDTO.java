package com.forumhub.api.dto.author;

import jakarta.validation.constraints.NotBlank;

public record NewAuthorDTO(
    @NotBlank(message = "O nome do autor é obrigatório") String name,
    @NotBlank(message = "O email do autor é obrigatório") String email) {
}
