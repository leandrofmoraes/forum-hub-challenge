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

import com.forumhub.api.dto.course.CourseDetailedDTO;
import com.forumhub.api.dto.course.NewCourseDTO;
import com.forumhub.api.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("courses")
public class CourseController {

  @Autowired
  private CourseService service;

  public ResponseEntity<CourseDetailedDTO> newCourse(@RequestBody @Valid NewCourseDTO newCourseDTO,
      UriComponentsBuilder uriBuilder) {
    var course = service.newCourse(newCourseDTO);
    var uri = uriBuilder.path("/courses/{id}").buildAndExpand(course.id()).toUri();
    return ResponseEntity.created(uri).body(course);
  }

  public ResponseEntity<CourseDetailedDTO> findCourse(@PathVariable(value = "id") Long id) {
    var course = service.findCourse(id);
    return ResponseEntity.status(HttpStatus.OK).body(course);
  }

  public ResponseEntity<Page<CourseDetailedDTO>> listCourses(
      @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

    var page = service.listCourses(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(page);
  }
}
