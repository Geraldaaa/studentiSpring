package com.studenti.studentiSpring.services;

import com.studenti.studentiSpring.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO dto);
    TeacherDTO updateTeacher(Long id, TeacherDTO dto);
    void deleteTeacher(Long id);
    TeacherDTO getTeacher(Long id);
    List<TeacherDTO> getAllTeachers();

}
