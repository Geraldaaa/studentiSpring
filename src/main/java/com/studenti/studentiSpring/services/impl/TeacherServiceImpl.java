package com.studenti.studentiSpring.services.impl;

import com.studenti.studentiSpring.dto.TeacherDTO;
import com.studenti.studentiSpring.models.Teacher;
import com.studenti.studentiSpring.repositories.TeacherRepository;
import com.studenti.studentiSpring.services.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {


    private TeacherRepository repo;

    public TeacherServiceImpl(TeacherRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO dto) {

        Teacher teacher = new Teacher();

        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setAge(dto.getAge());

        Teacher savedTeacher = repo.save(teacher);

        return new TeacherDTO(savedTeacher.getFirstName(), savedTeacher.getLastName(), savedTeacher.getEmail(), savedTeacher.getAge());

    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO dto) {
        Teacher teacher = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setAge(dto.getAge());

        Teacher updatedTeacher = repo.save(teacher);

        return new TeacherDTO(
                updatedTeacher.getId(),
                updatedTeacher.getFirstName(),
                updatedTeacher.getLastName(),
                updatedTeacher.getEmail(),
                updatedTeacher.getAge()
        );
    }


    @Override
    public void deleteTeacher(Long id) {
        repo.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        repo.deleteById(id);
    }

    @Override
    public TeacherDTO getTeacher(Long id) {
        Teacher t = repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return new TeacherDTO(t.getId(), t.getFirstName(), t.getLastName(), t.getEmail(), t.getAge());
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return repo.findAll().stream()
                .map(teacher -> new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastName(), teacher.getEmail(), teacher.getAge()))
                .collect(Collectors.toList());
    }

}
