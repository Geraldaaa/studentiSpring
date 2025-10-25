package com.studenti.studentiSpring.repositories;
import com.studenti.studentiSpring.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
