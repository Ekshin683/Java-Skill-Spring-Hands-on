package com.library;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementBootApplication.class, args);
    }

    // Pre-load some books into H2 on startup
    @Bean
    public CommandLineRunner loadData(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("Clean Code", "Robert Martin", "978-0132350884"));
            bookRepository.save(new Book("The Pragmatic Programmer", "David Thomas", "978-0201616224"));
            bookRepository.save(new Book("1984", "George Orwell", "978-0451524935"));
            bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0061935466"));
            System.out.println("Sample books loaded into database.");
        };
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookService(bookRepository);
    }
}