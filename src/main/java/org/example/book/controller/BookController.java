package org.example.book.controller;

import org.example.book.domain.Book;
import org.example.book.dto.RequestBook;
import org.example.book.dto.ResponseBook;
import org.example.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBook create (@RequestBody RequestBook requestBook){
        ResponseBook responseBook = bookService.save(requestBook);
        return responseBook;
    }

    @GetMapping
    //find all의 데이터 타입은 무엇인가?
    public List<ResponseBook> findall(){
        return bookService.findall();
    }

    @GetMapping("{id}")
    public ResponseBook findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PutMapping("{id}")
    public ResponseBook update(@PathVariable Long id, @RequestBody RequestBook requestBook){
        return bookService.update(id, requestBook);
    }

}
