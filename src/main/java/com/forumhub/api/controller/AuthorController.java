package com.forumhub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.forumhub.api.dto.author.AuthorDetailedDTO;
import com.forumhub.api.dto.author.NewAuthorDTO;
import com.forumhub.api.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("authors")
public class AuthorController {

  @Autowired
  private AuthorService service;

  public ResponseEntity<AuthorDetailedDTO> newAuthor(@RequestBody @Valid NewAuthorDTO newAuthorDTO,
      UriComponentsBuilder uriBuilder) {
    var author = service.create(newAuthorDTO);
    var uri = uriBuilder.path("/authors/{id}").buildAndExpand(author.id()).toUri();
    return ResponseEntity.created(uri).body(author);
  }

  public ResponseEntity<AuthorDetailedDTO> findAuthor(@PathVariable(value = "id") Long id) {
    var author = service.findAuthor(id);
    return ResponseEntity.status(HttpStatus.OK).body(author);
  }

  public ResponseEntity<Page<AuthorDetailedDTO>> listAuthors(
      @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

    var page = service.listAuthors(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(page);
  }
}
