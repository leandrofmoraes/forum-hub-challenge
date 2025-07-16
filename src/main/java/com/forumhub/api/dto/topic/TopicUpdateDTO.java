package com.forumhub.api.dto.topic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
    @NotNull(message = "O ID do tópico é obrigatório") Long id,
    String title,
    String content,
    Boolean status) {
}
