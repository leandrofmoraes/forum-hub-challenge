package com.forumhub.api.entity;

import java.time.LocalDateTime;

import com.forumhub.api.dto.topic.TopicCreateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topics")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @Column(name = "message", columnDefinition = "TEXT")
  private String content;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  private boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  public Topic(TopicCreateDTO topicCreateDTO, Author author, Course course) {
    this.title = topicCreateDTO.title();
    this.content = topicCreateDTO.content();
    this.createdAt = LocalDateTime.now();
    this.status = true;
    this.author = author;
    this.course = course;
  }

  public void setTitle(String title) {
    if (title != null && !title.isBlank()) {
      this.title = title;
    }
  }

  public void setContent(String content) {
    if (content != null) {
      this.content = content;
    }
  }

  public void setStatus(Boolean status) {
    if (status != null) {
      this.status = status;
    }
  }
}
