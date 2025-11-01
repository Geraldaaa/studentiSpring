package com.studenti.studentiSpring.services;

import com.studenti.studentiSpring.dto.CourseDTO;

import java.util.List;


public interface CourseService {

    CourseDTO addCourse(CourseDTO dto);
    CourseDTO updateCourse(Long id, CourseDTO dto);
    void deleteCourse(Long id);
    List<CourseDTO> getAllCourses();
    CourseDTO getCourseById(Long id);

}
