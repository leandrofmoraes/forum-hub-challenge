package com.forumhub.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.forumhub.api.dto.author.AuthorDetailedDTO;
import com.forumhub.api.dto.author.NewAuthorDTO;
import com.forumhub.api.entity.Author;
import com.forumhub.api.repository.AuthorRepository;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository repository;

  public AuthorDetailedDTO create(NewAuthorDTO newAuthorDTO) {
    Author author = new Author(newAuthorDTO);
    repository.save(author);
    return new AuthorDetailedDTO(author);
  }

  public AuthorDetailedDTO findAuthor(Long id) {
    var author = repository.getReferenceById(id);
    return new AuthorDetailedDTO(author);
  }

  public Page<AuthorDetailedDTO> listAuthors(Pageable pageable) {
    return repository.findAll(pageable).map(AuthorDetailedDTO::new);
  }
}
