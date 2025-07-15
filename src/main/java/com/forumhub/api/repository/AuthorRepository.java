package com.forumhub.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumhub.api.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
