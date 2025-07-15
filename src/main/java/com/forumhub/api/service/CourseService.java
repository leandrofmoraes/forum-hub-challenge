package com.forumhub.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.forumhub.api.dto.course.CourseDetailedDTO;
import com.forumhub.api.dto.course.NewCourseDTO;
import com.forumhub.api.entity.Course;
import com.forumhub.api.repository.CourseRepository;

@Service
public class CourseService {

  @Autowired
  private CourseRepository repository;

  public CourseDetailedDTO newCourse(NewCourseDTO newCourseDTO) {
    Course course = new Course(newCourseDTO);
    repository.save(course);
    return new CourseDetailedDTO(course);
  }

  public CourseDetailedDTO findCourse(Long id) {
    var course = repository.getReferenceById(id);
    return new CourseDetailedDTO(course);
  }

  public Page<CourseDetailedDTO> listCourses(Pageable pageable) {
    return repository.findAll(pageable).map(CourseDetailedDTO::new);
  }
}
