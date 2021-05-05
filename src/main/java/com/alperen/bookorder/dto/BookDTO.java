package com.alperen.bookorder.dto;

import com.alperen.bookorder.model.Book;

public class BookDTO {

    private String id;

    private String name;

    private Integer stockCount;

    public BookDTO() {
    }

    public BookDTO(String id, String name, Integer stockCount) {
        this.id = id;
        this.name = name;
        this.stockCount = stockCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Book convertToEntity() {
        Book book = new Book();
        book.setName(getName());
        book.setStockCount(getStockCount());
        return book;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stockCount=" + stockCount +
                '}';
    }
}
