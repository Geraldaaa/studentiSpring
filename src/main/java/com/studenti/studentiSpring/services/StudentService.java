package com.studenti.studentiSpring.services;

import com.studenti.studentiSpring.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO dto);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long id, StudentDTO dto);
    void deleteStudent(Long id);
}