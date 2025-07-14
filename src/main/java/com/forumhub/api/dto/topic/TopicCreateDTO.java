package com.forumhub.api.dto.topic;

import com.forumhub.api.entity.Author;
import com.forumhub.api.entity.Course;

public record TopicCreateDTO(
    String title,
    String content,
    Author author,
    Course course) {
}
