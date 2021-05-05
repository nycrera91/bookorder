package com.alperen.bookorder.model;

import com.alperen.bookorder.enums.OrderStatus;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.Date;

@Document
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @Field
    private String id;

    @Field
    private String customerId;

    @Field
    private String bookId;

    @Field
    private Date orderDate;

    @Field
    private OrderStatus status;

    public Order() {
    }

    public Order(String id, String customerId, String bookId, Date orderDate, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}';
    }
}
