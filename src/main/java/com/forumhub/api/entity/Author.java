package com.forumhub.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.forumhub.api.dto.author.NewAuthorDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private List<Topic> topics = new ArrayList<>();

  public Author(NewAuthorDTO newAuthor) {
    this.name = newAuthor.name();
    this.email = newAuthor.email();
  }
}
