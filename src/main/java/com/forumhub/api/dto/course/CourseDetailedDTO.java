package com.forumhub.api.dto.course;

import com.forumhub.api.entity.Course;

public record CourseDetailedDTO(
    Long id,
    String name) {

  public CourseDetailedDTO(Course course) {
    this(
        course.getId(),
        course.getName());
  }

}
