package com.alperen.bookorder.repository;

import com.alperen.bookorder.exception.NotFoundException;
import com.alperen.bookorder.model.Book;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public static final String STOCK_COUNT = "stockCount";
    public static final String NOT_FOUND = "Book";

    private final CouchbaseTemplate couchbaseTemplate;

    public BookRepository(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    public void save(Book book) {
        couchbaseTemplate.save(book);
    }

    public void update(Book book) {
        Bucket bucket = couchbaseTemplate.getCouchbaseBucket();

        JsonDocument bookDocument = bucket.getAndLock(book.getId(), 10);
        int lastStockCount = book.getStockCount() -1;
        bookDocument.content().put(STOCK_COUNT, lastStockCount);
        bucket.replace(bookDocument);
    }

    public Book findById(String bookId) {
        Book book = couchbaseTemplate.findById(bookId, Book.class);

        if(book == null) {
            throw new NotFoundException(NOT_FOUND, bookId);
        }
        return book;
    }
}
