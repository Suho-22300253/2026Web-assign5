package org.example.book.service;

import org.example.book.domain.Book;
import org.example.book.dto.RequestBook;
import org.example.book.dto.ResponseBook;
import org.example.book.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public ResponseBook save(RequestBook requestBook){

        Book book = new Book();
        book.setName(requestBook.name());
        book.setAuthor(requestBook.author());
        book.setPrice(requestBook.price());
        book.setCode(requestBook.code());
        book.setGenre(requestBook.genre());

        Book reBook = repository.save(book);

        ResponseBook responseBook = new ResponseBook(
                reBook.getId(),
                reBook.getName(),
                reBook.getAuthor(),
                reBook.getPrice(),
                reBook.getCode(),
                reBook.getGenre()
        );
        return responseBook;
    }

    public List<ResponseBook> findall(){
        List<ResponseBook> BoookResponses = new ArrayList<>();
        for(Book book : repository.findall()){
            ResponseBook responseBook = new ResponseBook(
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getCode(),
                    book.getGenre()
            );

            BoookResponses.add(responseBook);
        }
        return BoookResponses;
    }

    public ResponseBook findById(Long id){

            //optional은 어떻게 받아야 하는가?
            Book book = repository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Student not found"
                    ));
            ResponseBook responseBook = new ResponseBook(
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getCode(),
                    book.getGenre()
            );


        return responseBook;
    }

    public ResponseBook update(RequestBook requestBook){
        return null;
    }

    public void delete(Long id){

    }
}
