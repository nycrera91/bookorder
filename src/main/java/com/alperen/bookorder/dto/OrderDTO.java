package com.alperen.bookorder.dto;

import com.alperen.bookorder.enums.OrderStatus;
import com.alperen.bookorder.model.Order;

import java.util.Date;

public class OrderDTO {

    private String bookId;

    private String orderId;

    private String customerId;

    private OrderStatus orderStatus;

    private Date orderDate;

    public OrderDTO() {
    }

    public OrderDTO(String bookId, String orderId, String customerId, OrderStatus orderStatus, Date orderDate) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order convertToEntityWithBookIdAndCustomerId(String bookId, String customerId) {
        Order order = new Order();

        order.setBookId(bookId);
        order.setCustomerId(customerId);
        order.setStatus(OrderStatus.NEW);
        order.setOrderDate(new Date());

        return order;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "bookId='" + bookId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                '}';
    }
}
