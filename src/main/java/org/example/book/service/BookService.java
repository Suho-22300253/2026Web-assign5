package org.example.book.service;

import org.example.book.domain.Book;
import org.example.book.dto.RequestBook;
import org.example.book.dto.ResponseBook;
import org.example.book.repository.BookRepository;
import org.springframework.stereotype.Service;

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

    public List<Book> findall(){
        return null;
    }

    public ResponseBook findById(Long id){
        return null;
    }

    public ResponseBook update(RequestBook requestBook){
        return null;
    }

    public void delete(Long id){

    }
}
