package org.example.book.repository;

import org.example.book.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryBookRepository implements BookRepository {

    private final Map<Long, Book> repo = new LinkedHashMap<>();
    private Long sequence = 0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        repo.put(book.getId(), book);
        return book;
    }

    @Override
    public List<Book> findall() {
        List<Book> books = new ArrayList<>();

        //Map을 처음부터 가면서 하나씩 읽는 방법 설명:
        for (Map.Entry<Long, Book> entry : repo.entrySet()) {
            books.add(entry.getValue());
        }
        return List.of();
    }

    @Override
    public Optional<Book> findById(Long id) {

        //ofNillable 함수의 역할:
        return Optional.ofNullable(repo.get(id));
    }

    @Override
    public Book update(Book book) {

        //값이 있어야 처리 가능하기 때문
        if (findById(book.getId()).isPresent()) {
            repo.put(book.getId(), book);
            return book;
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
