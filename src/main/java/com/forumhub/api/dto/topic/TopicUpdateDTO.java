package com.forumhub.api.dto.topic;

public record TopicUpdateDTO(
    Long id,
    String title,
    String content,
    Boolean status) {
}
