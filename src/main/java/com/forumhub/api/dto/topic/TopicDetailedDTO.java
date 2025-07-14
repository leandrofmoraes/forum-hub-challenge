package com.forumhub.api.dto.topic;

import java.time.LocalDateTime;

import com.forumhub.api.entity.*;

public record TopicDetailedDTO(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    boolean status,
    Author author,
    Course course) {

  public TopicDetailedDTO(Topic topic) {
    this(
        topic.getId(),
        topic.getTitle(),
        topic.getContent(),
        topic.getCreatedAt(),
        topic.isStatus(),
        topic.getAuthor(),
        topic.getCourse());
  }
}
