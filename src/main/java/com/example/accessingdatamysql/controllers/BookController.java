package com.example.accessingdatamysql.controllers;

import com.example.accessingdatamysql.entities.Book;
import com.example.accessingdatamysql.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Integer id) {
        Optional<Book> bookEntity = bookRepository.findById(id);
        return bookEntity.orElse(null);
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Integer id) {
        return bookRepository.findById(id).map( book -> {
                    book.setName(newBook.getName());
                    book.setNumberOfPages(newBook.getNumberOfPages());
                    book.setAuthor(newBook.getAuthor());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }

}
