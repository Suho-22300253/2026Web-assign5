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

        check(requestBook);

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
        Student student = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));
        ResponseStudent responseStudent = new ResponseStudent(
                student.getId(),
                student.getName(),
                student.getStudentId(),
                student.getSemester(),
                student.getMajor(),
                student.getRc()
        );


        return responseStudent;
    }

    public ResponseStudent findByName(String name) {
        Student student = repository.findByName(name);

        if(student == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseStudent responseStudent = new ResponseStudent(
                student.getId(),
                student.getName(),
                student.getStudentId(),
                student.getSemester(),
                student.getMajor(),
                student.getRc()
        );


        return responseStudent;
    }

    public ResponseStudent update(Long id, RequestStudent requestBook) {

        check(requestBook);

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



    private void check (RequestStudent request){ // 필드가 빠진 경우 기본적으로 null 이나 0 이 들어가지기 때문에 이러한 검사가 필요하다.
        if(request.name() == null || request.name().isBlank()){ // "" 도 null 이기 떄문에 isBlank로 "" 를 잡는다.

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
            );
        }

        if(request.studentId() == null || !request.studentId().matches("\\d{8}")){ // 정확히 8자리 숫자여야 한다
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
            );
        }
        if(request.major() == null || request.major().isBlank()){

            throw new ResponseStatusException( // null 일 경우 예외 발생
                    HttpStatus.BAD_REQUEST
            );
        }
        if(request.semester() < 1){ // primitive int라서 기본값 0이 들어올 수 있기 때문에 누락도 잡아낸다.

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
            );
        }
        if(request.rc() == null || request.rc().isBlank()){

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
            );
        }

    }


}
