package com.forumhub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.forumhub.api.dto.author.AuthorDetailedDTO;
import com.forumhub.api.dto.author.AuthorUpdateDTO;
import com.forumhub.api.dto.author.NewAuthorDTO;
import com.forumhub.api.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("authors")
public class AuthorController {

  @Autowired
  private AuthorService service;

  @PostMapping
  public ResponseEntity<AuthorDetailedDTO> newAuthor(@RequestBody @Valid NewAuthorDTO newAuthorDTO,
      UriComponentsBuilder uriBuilder) {
    var author = service.create(newAuthorDTO);
    var uri = uriBuilder.path("/authors/{id}").buildAndExpand(author.id()).toUri();
    return ResponseEntity.created(uri).body(author);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDetailedDTO> findAuthor(@PathVariable(value = "id") Long id) {
    var author = service.findAuthor(id);
    return ResponseEntity.status(HttpStatus.OK).body(author);
  }

  @GetMapping
  public ResponseEntity<Page<AuthorDetailedDTO>> listAuthors(
      @PageableDefault(size = 10, sort = "name") Pageable pageable) {

    var page = service.listAuthors(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(page);
  }

  @PutMapping
  public ResponseEntity<String> updatePassword(@RequestBody @Valid AuthorUpdateDTO authorUpdateDTO) {
    service.updatePassword(authorUpdateDTO);
    return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
  }
}
