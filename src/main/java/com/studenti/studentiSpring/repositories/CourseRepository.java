package com.studenti.studentiSpring.repositories;

import com.studenti.studentiSpring.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
