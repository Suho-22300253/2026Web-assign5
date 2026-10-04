package org.example.book.repository;

import org.example.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    List<Book> findall();
    Optional<Book> findById(Long id);
    Book update(Book book);
    void deleteById(Long id);

}
