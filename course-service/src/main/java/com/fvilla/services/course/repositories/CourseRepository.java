package com.fvilla.services.course.repositories;

import com.fvilla.services.course.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
