package com.forumhub.api.dto.author;

import jakarta.validation.constraints.NotNull;

public record AuthorUpdateDTO(
    @NotNull(message = "O ID do tópico é obrigatório") Long id,
    String password) {

}
