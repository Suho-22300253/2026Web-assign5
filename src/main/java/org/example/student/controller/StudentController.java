package org.example.student.controller;

import org.example.student.dto.RequestStudent;
import org.example.student.dto.ResponseStudent;
import org.example.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseStudent create (@RequestBody RequestStudent requestStudent){
        ResponseStudent responseStudent = studentService.save(requestStudent);
        return responseStudent;
    }

    @GetMapping
    //find all의 데이터 타입은 무엇인가?
    public List<ResponseStudent> findall(){
        return studentService.findall();
    }

    @GetMapping("/{id}")
    public ResponseStudent findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @GetMapping("name/{name}") // 둘 다 같은 패턴이라 충돌가능 {id}와 {name}이라는 변수 이름은 URL을 구분하는 기준이 아니다.
    public ResponseStudent findByName(@PathVariable String name){ return studentService.findByName(name);}

    @PutMapping("/{id}")
    public ResponseStudent update(@PathVariable Long id, @RequestBody RequestStudent requestStudent){
        return studentService.update(id, requestStudent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }

}
