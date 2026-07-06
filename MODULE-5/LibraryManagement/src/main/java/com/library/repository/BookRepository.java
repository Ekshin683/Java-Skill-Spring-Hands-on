package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

// Exercise 6 — @Repository annotation for component scanning
@Repository
public class BookRepository {

    // Simulated in-memory book store
    private List<String> books = new ArrayList<>();

    public BookRepository() {
        // Pre-load some books
        books.add("The Great Gatsby");
        books.add("To Kill a Mockingbird");
        books.add("1984");
        books.add("Clean Code");
    }

    public List<String> findAllBooks() {
        System.out.println("[BookRepository] Fetching all books from repository...");
        return books;
    }

    public void addBook(String bookTitle) {
        System.out.println("[BookRepository] Adding book: " + bookTitle);
        books.add(bookTitle);
    }

    public boolean removeBook(String bookTitle) {
        System.out.println("[BookRepository] Removing book: " + bookTitle);
        return books.remove(bookTitle);
    }

    public boolean bookExists(String bookTitle) {
        return books.contains(bookTitle);
    }
}