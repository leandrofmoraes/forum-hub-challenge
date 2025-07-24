package com.forumhub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forumhub.api.dto.author.AuthenticationDTO;
import com.forumhub.api.entity.Author;
import com.forumhub.api.infra.security.TokenDTO;
import com.forumhub.api.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private TokenService service;

  @PostMapping
  public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
    var authentication = authManager.authenticate(authenticationToken);

    var tokenJWT = service.generateToken((Author) authentication.getPrincipal());
    return ResponseEntity.ok(new TokenDTO(tokenJWT));
  }
}
