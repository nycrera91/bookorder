package com.alperen.bookorder.service;

import com.alperen.bookorder.dto.BookDTO;
import com.alperen.bookorder.model.Book;
import com.alperen.bookorder.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(BookDTO bookDTO) {
        Book book = bookDTO.convertToEntity();

        bookRepository.save(book);

        LOGGER.info("Book is added. Book information is {}", book);
    }

    public BookDTO getBook(String bookId) {
        Book book = bookRepository.findById(bookId);
        BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), book.getStockCount());
        LOGGER.info("Get book information {}", bookDTO);
        return bookDTO;
    }

    public void update(Book book) {
        bookRepository.update(book);
    }
}
