package com.studenti.studentiSpring.services.impl;

import com.studenti.studentiSpring.dto.StudentDTO;
import com.studenti.studentiSpring.models.Address;
import com.studenti.studentiSpring.models.Course;
import com.studenti.studentiSpring.models.Student;
import com.studenti.studentiSpring.repositories.AddressRepository;
import com.studenti.studentiSpring.repositories.CourseRepository;
import com.studenti.studentiSpring.repositories.StudentRepository;
import com.studenti.studentiSpring.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;


    public StudentServiceImpl(StudentRepository repository, AddressRepository addressRepository, CourseRepository courseRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Set<Course> courses = new HashSet<>();
        if (dto.getCourseIds() != null && !dto.getCourseIds().isEmpty()) {
            courses.addAll(courseRepository.findAllById(dto.getCourseIds()));
        }

        Student student = new Student(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getDateOfBirth(),address, courses);

        Student saved = repository.save(student);
        return new StudentDTO(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail(), saved.getDateOfBirth(), saved.getAddress().getId(), saved.getCourses().stream().map(Course::getId).collect(Collectors.toCollection(HashSet::new)));


    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student s = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return new StudentDTO(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getDateOfBirth(), s.getAddress().getId());
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return repository.findAll().stream()
                .map(s -> new StudentDTO(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getDateOfBirth(), s.getAddress().getId()))
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
        return new StudentDTO(updated.getId(), updated.getFirstName(), updated.getLastName(), updated.getEmail(), updated.getDateOfBirth(), updated.getAddress().getId());
    }

    @Override
    public void deleteStudent(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        repository.deleteById(id);
    }
}