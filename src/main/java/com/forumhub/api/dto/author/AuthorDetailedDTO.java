package com.forumhub.api.dto.author;

import com.forumhub.api.entity.Author;

public record AuthorDetailedDTO(
    Long id,
    String name,
    String email) {

  public AuthorDetailedDTO(Author author) {
    this(
        author.getId(),
        author.getName(),
        author.getEmail());
  }

}
