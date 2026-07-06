package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;

public class BookService {

	private BookRepository bookRepository;

	public BookService() {
	}

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<String> getAllBooks() {
		return bookRepository.findAllBooks();
	}

	public void addBook(String bookTitle) {
		bookRepository.addBook(bookTitle);
	}

	public boolean isBookAvailable(String bookTitle) {
		return bookRepository.bookExists(bookTitle);
	}

	public boolean removeBook(String bookTitle) {
		return bookRepository.removeBook(bookTitle);
	}
}
