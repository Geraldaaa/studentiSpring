package com.studenti.studentiSpring.services.impl;

import com.studenti.studentiSpring.dto.CourseDTO;
import com.studenti.studentiSpring.models.Course;
import com.studenti.studentiSpring.models.Teacher;
import com.studenti.studentiSpring.repositories.CourseRepository;
import com.studenti.studentiSpring.repositories.TeacherRepository;
import com.studenti.studentiSpring.services.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final TeacherRepository teacherRepository;


    public CourseServiceImpl(CourseRepository repository, TeacherRepository teacherRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public CourseDTO addCourse(CourseDTO dto) {
        Teacher t = teacherRepository.findById(dto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        course.setCourseDescription(dto.getCourseDescription());
        course.setTeacher(t);

        Course s = repository.save(course);
       return new CourseDTO(s.getId(), s.getCourseName(), s.getCourseCode(), s.getCourseDescription(), s.getTeacher().getId());
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Optional<Course> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }

        Teacher t = teacherRepository.findById(dto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found"));
        Course course = optional.get();
        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        course.setCourseDescription(dto.getCourseDescription());
        course.setTeacher(t);

        Course updated = repository.save(course);
        return new CourseDTO(updated.getId(), updated.getCourseName(), updated.getCourseCode(), updated.getCourseDescription(), updated.getTeacher().getId());
    }

    @Override
    public void deleteCourse(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        repository.deleteById(id);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return repository.findAll().stream()
                .map(c -> new CourseDTO(c.getId(),c.getCourseName(), c.getCourseCode(), c.getCourseDescription(), c.getTeacher().getId() ))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return new CourseDTO(s.getId(), s.getCourseName(), s.getCourseCode(), s.getCourseDescription(), s.getTeacher().getId());
    }

}
