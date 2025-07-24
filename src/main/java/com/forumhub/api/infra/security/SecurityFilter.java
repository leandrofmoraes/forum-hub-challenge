package com.forumhub.api.infra.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.forumhub.api.repository.AuthorRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private static final String BEARER_PREFIX = "Bearer ";
  @Autowired
  private TokenService service;
  @Autowired
  private AuthorRepository authorRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {

    retrieveToken(request)
        .ifPresent(token -> {
          var subject = service.getSubject(token);
          var author = authorRepository.findByEmail(subject);
          var authentication = new UsernamePasswordAuthenticationToken(author, null, author.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        });

    filterChain.doFilter(request, response);
  }

  private Optional<String> retrieveToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
        .filter(header -> header.startsWith(BEARER_PREFIX))
        .map(header -> header.substring(BEARER_PREFIX.length()).trim());
  }
}
