package org.example.student.repository;

import org.example.student.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryStudentRepository implements StudentRepository {

    private final Map<Long, Student> repo = new LinkedHashMap<>();
    private Long sequence = 0L;

    @Override
    public Student save(Student student) {
        student.setId(++sequence);
        repo.put(student.getId(), student);
        return student;
    }

    @Override
    public List<Student> findall() {
        List<Student> students = new ArrayList<>();

        //Map을 처음부터 가면서 하나씩 읽는 방법 설명:
        for (Map.Entry<Long, Student> entry : repo.entrySet()) {
            students.add(entry.getValue());
        }
        return students;
    }

    @Override
    public Optional<Student> findById(Long id) {

        //ofNillable 함수의 역할:
        return Optional.ofNullable(repo.get(id));
    }

    @Override
    public Student update(Student student) {

        //값이 있어야 처리 가능하기 때문
        if (findById(student.getId()).isPresent()) {
            repo.put(student.getId(), student);
            return student;
        }

        return null; // null을 보냈을 때 상위 함수에서 어떻게 처리하는지 확인 필요
    }

    @Override
    public void deleteById(Long id) {
        if (findById(id).isPresent()) {
            repo.remove(id);
        }
    }
}
