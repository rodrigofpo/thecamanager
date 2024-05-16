package com.demo.thecamanager.controllers;

import com.demo.thecamanager.dto.BookDTO;
import com.demo.thecamanager.dto.UserDTO;
import com.demo.thecamanager.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @DeleteMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBook(@RequestBody String bookID, UserDTO user) {
        return ResponseEntity.ok(bookService.deleteBook(bookID, user));
    }

    @PostMapping(value = "/add", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addBook(@RequestBody @Valid BookDTO book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PostMapping(value = "/update", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateBook(@RequestBody @Valid BookDTO book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity bookList() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(value = "/list/detail", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity bookDetail(@RequestBody BookDTO book) {
        return ResponseEntity.ok(bookService.getOneBook(book));
    }
}
