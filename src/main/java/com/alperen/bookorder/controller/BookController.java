package com.alperen.bookorder.controller;

import com.alperen.bookorder.dto.BookDTO;
import com.alperen.bookorder.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
    }

    @GetMapping("{bookId}")
    public BookDTO getBook(@PathVariable String bookId) {
        return bookService.getBook(bookId);
    }

}
