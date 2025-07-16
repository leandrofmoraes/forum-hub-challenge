package com.forumhub.api.dto.topic;

import com.forumhub.api.entity.Author;
import com.forumhub.api.entity.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateDTO(
    String title,
    @NotBlank(message = "O campo mensagem não pode estar vazio") String content,
    @NotNull(message = "O autor é obrigatório") Author author,
    @NotNull(message = "O curso é obrigatório") Course course) {
}
