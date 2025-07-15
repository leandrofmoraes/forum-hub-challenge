package com.forumhub.api.entity;

import com.forumhub.api.dto.author.NewAuthorDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;

  public Author(NewAuthorDTO newAuthor) {
    this.name = newAuthor.name();
    this.email = newAuthor.email();
  }
}
