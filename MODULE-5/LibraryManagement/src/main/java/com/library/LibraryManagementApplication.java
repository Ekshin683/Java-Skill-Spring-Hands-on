package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("  Library Management System Starting...");
        System.out.println("========================================\n");

        // Exercise 1 — Load Spring ApplicationContext from XML
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n--- Exercise 2, 5: Testing Dependency Injection ---");

        // Use the constructor-injected service bean for the main demo.
        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);

        // Exercise 3 & 8 — All method calls below will be intercepted by LoggingAspect

        System.out.println("\n--- Fetching all books ---");
        List<String> books = bookService.getAllBooks();
        books.forEach(b -> System.out.println("  > " + b));

        System.out.println("\n--- Adding a new book ---");
        bookService.addBook("The Pragmatic Programmer");

        System.out.println("\n--- Checking book availability ---");
        boolean available = bookService.isBookAvailable("1984");
        System.out.println("  Is '1984' available? " + available);

        System.out.println("\n--- Removing a book ---");
        boolean removed = bookService.removeBook("The Great Gatsby");
        System.out.println("  Was 'The Great Gatsby' removed? " + removed);

        System.out.println("\n--- Final book list ---");
        bookService.getAllBooks().forEach(b -> System.out.println("  > " + b));

        System.out.println("\n--- Exercise 7: Testing Constructor vs Setter Injection ---");

        // Constructor injection bean
        BookService constructorInjected =
                context.getBean("bookServiceConstructor", BookService.class);
        System.out.println("Constructor injected service ready: "
                + (constructorInjected != null));

        // Setter injection bean
        BookService setterInjected =
                context.getBean("bookServiceSetter", BookService.class);
        System.out.println("Setter injected service ready: "
                + (setterInjected != null));

        System.out.println("\n========================================");
        System.out.println("  Application Finished.");
        System.out.println("========================================");

        // Close the context to release resources
        ((ClassPathXmlApplicationContext) context).close();
    }
}