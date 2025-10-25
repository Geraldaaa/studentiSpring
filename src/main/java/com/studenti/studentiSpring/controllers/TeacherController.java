package com.studenti.studentiSpring.controllers;

import com.studenti.studentiSpring.dto.TeacherDTO;
import com.studenti.studentiSpring.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService service;


    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@Validated @RequestBody TeacherDTO dto) {
        return new ResponseEntity<>(service.createTeacher(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeacher(id));
    }

    @GetMapping()
    public ResponseEntity<List<TeacherDTO>> getById() {
        return ResponseEntity.ok(service.getAllTeachers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@PathVariable Long id, @Validated @RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(service.updateTeacher(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }



}


