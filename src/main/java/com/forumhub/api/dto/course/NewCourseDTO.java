package com.forumhub.api.dto.course;

import jakarta.validation.constraints.NotBlank;

public record NewCourseDTO(
    @NotBlank(message = "O nome do curso é obrigatório") String name) {
}
