package com.forumhub.api.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateDTO(
    String title,
    @NotBlank(message = "O campo mensagem não pode estar vazio") String content,
    @NotNull(message = "O autor é obrigatório") Long authorId,
    @NotNull(message = "O curso é obrigatório") Long courseId) {
}
