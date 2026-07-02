package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void performServiceTask() {
        System.out.println("BookService: Service task started.");
        if (bookRepository != null) {
            System.out.println("BookService: BookRepository dependency injected successfully!");
            bookRepository.performRepositoryTask();
        } else {
            System.out.println("BookService: BookRepository is not initialized (DI failed).");
        }
    }
}
