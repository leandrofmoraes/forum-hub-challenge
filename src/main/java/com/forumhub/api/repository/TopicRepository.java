package com.forumhub.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumhub.api.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
