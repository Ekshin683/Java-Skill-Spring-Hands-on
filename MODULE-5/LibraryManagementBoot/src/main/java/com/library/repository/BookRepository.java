package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Spring Data generates the SQL automatically from method names
    List<Book> findByAuthor(String author);
    List<Book> findByAvailable(boolean available);
    List<Book> findByTitleContainingIgnoreCase(String keyword);
    boolean existsByIsbn(String isbn);
}