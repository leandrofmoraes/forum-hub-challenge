package com.forumhub.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forumhub.api.dto.author.AuthorDetailedDTO;
import com.forumhub.api.dto.author.AuthorUpdateDTO;
import com.forumhub.api.dto.author.NewAuthorDTO;
import com.forumhub.api.entity.Author;
import com.forumhub.api.repository.AuthorRepository;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthorDetailedDTO create(NewAuthorDTO newAuthorDTO) {
    Author author = new Author(
        newAuthorDTO.name(),
        newAuthorDTO.email(),
        encodePassword(newAuthorDTO.password()));

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

  @Transactional
  public void updatePassword(AuthorUpdateDTO authorUpdateDTO) {
    Author author = repository.findById(authorUpdateDTO.id())
        .orElseThrow(() -> new IllegalArgumentException("Topic with ID " + authorUpdateDTO.id() + " does not exist."));

    author.setPassword(encodePassword(authorUpdateDTO.password()));
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
