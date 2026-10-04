package org.example.student.repository;

import org.example.student.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);
    List<Student> findall();
    Optional<Student> findById(Long id);
    Student update(Student student);
    void deleteById(Long id);

}
