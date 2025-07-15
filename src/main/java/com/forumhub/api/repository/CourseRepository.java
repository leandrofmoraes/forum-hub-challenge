package com.forumhub.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumhub.api.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
