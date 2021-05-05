package com.alperen.bookorder.model;

import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Document
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private Integer stockCount;


    public Book() {
    }

    public Book(String id, String name, Integer stockCount) {
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

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stockCount=" + stockCount +
                '}';
    }
}
