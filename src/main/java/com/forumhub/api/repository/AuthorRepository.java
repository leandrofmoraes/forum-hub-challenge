package com.forumhub.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.forumhub.api.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  UserDetails findByEmail(String subject);
}
