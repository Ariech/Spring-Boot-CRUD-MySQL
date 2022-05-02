package com.example.accessingdatamysql.controllers;

import com.example.accessingdatamysql.entities.Book;
import com.example.accessingdatamysql.exceptions.ResourceNotFoundException;
import com.example.accessingdatamysql.repositories.BookRepository;
import com.example.accessingdatamysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/users/{userId}/books")
    public ResponseEntity<List<Book>> getAllBooksByUserId(@PathVariable(value = "userId") Integer userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("No User with id = " + userId);
        }
        List<Book> books = bookRepository.findByUserId(userId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBooksById(@PathVariable(value = "id")Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Book with id = " + id));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/books")
    public ResponseEntity<Book> createBook(@PathVariable(value = "userId")Integer userId, @RequestBody Book bookRequest) {
        Book book = userRepository.findById(userId).map(user -> {
            bookRequest.setUser(user);
            return bookRepository.save(bookRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No User with id = " + userId));

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookId " + id + "not found"));

        book.setAuthor(bookRequest.getAuthor());
        book.setName(bookRequest.getName());
        book.setNumberOfPages(bookRequest.getNumberOfPages());

        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Integer id) {
        bookRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{userId}/books")
    public ResponseEntity<List<Book>> deleteAllBooksOfUser(@PathVariable(value = "userId") Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("No User with id = " + userId);
        }

        bookRepository.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
