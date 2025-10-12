package com.studenti.studentiSpring.services;

import com.studenti.studentiSpring.dto.StudentDTO;
import com.studenti.studentiSpring.models.Student;
import com.studenti.studentiSpring.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getDateOfBirth());
        Student saved = repository.save(student);
        return new StudentDTO(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail(), saved.getDateOfBirth());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student s = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return new StudentDTO(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getDateOfBirth());
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return repository.findAll().stream()
                .map(s -> new StudentDTO(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getDateOfBirth()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setDateOfBirth(dto.getDateOfBirth());

        Student updated = repository.save(existing);
        return new StudentDTO(updated.getId(), updated.getFirstName(), updated.getLastName(), updated.getEmail(), updated.getDateOfBirth());
    }

    @Override
    public void deleteStudent(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        repository.deleteById(id);
    }
}