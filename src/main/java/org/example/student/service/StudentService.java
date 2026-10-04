package org.example.student.service;


import org.example.student.domain.Student;
import org.example.student.dto.RequestStudent;
import org.example.student.dto.ResponseStudent;

import org.example.student.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public ResponseStudent save(RequestStudent requestBook) {

        Student book = new Student();
        book.setName(requestBook.name());
        book.setStudentId(requestBook.studentId());
        book.setSemester(requestBook.semester());
        book.setMajor(requestBook.major());
        book.setRc(requestBook.rc());

        Student reBook = repository.save(book);

        ResponseStudent responseBook = new ResponseStudent(
                reBook.getId(),
                reBook.getName(),
                reBook.getStudentId(),
                reBook.getSemester(),
                reBook.getMajor(),
                reBook.getRc()
        );
        return responseBook;
    }

    public List<ResponseStudent> findall() {
        List<ResponseStudent> BoookResponses = new ArrayList<>();
        for (Student book : repository.findall()) {
            ResponseStudent responseBook = new ResponseStudent(
                    book.getId(),
                    book.getName(),
                    book.getStudentId(),
                    book.getSemester(),
                    book.getMajor(),
                    book.getRc()
            );

            BoookResponses.add(responseBook);
        }
        return BoookResponses;
    }

    public ResponseStudent findById(Long id) {

        //optional은 어떻게 받아야 하는가?
        Student book = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));
        ResponseStudent responseBook = new ResponseStudent(
                book.getId(),
                book.getName(),
                book.getStudentId(),
                book.getSemester(),
                book.getMajor(),
                book.getRc()
        );


        return responseBook;
    }

    public ResponseStudent update(Long id, RequestStudent requestBook) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        ));
        student.setName(requestBook.name());
        student.setStudentId(requestBook.studentId());
        student.setSemester(requestBook.semester());
        student.setMajor(requestBook.major());
        student.setRc(requestBook.rc());

        ResponseStudent responseBook = new ResponseStudent(
                student.getId(),
                student.getName(),
                student.getStudentId(),
                student.getSemester(),
                student.getMajor(),
                student.getRc()
        );

        return responseBook;

    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        ));
        repository.deleteById(id);
    }
}
