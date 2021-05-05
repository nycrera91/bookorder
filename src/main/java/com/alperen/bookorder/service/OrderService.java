package com.alperen.bookorder.service;

import com.alperen.bookorder.dto.OrderDTO;
import com.alperen.bookorder.exception.NotFoundException;
import com.alperen.bookorder.exception.OrderAccessException;
import com.alperen.bookorder.exception.StockNotEnoughException;
import com.alperen.bookorder.model.Book;
import com.alperen.bookorder.model.Order;
import com.alperen.bookorder.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    public static final String NOT_FOUND_ORDER = "Order";

    private BookService bookService;

    private OrderRepository orderRepository;

    public OrderService(BookService bookService, OrderRepository orderRepository) {
        this.bookService = bookService;
        this.orderRepository = orderRepository;
    }

    public void saveOrder(String bookId, String customerId) {
        Book book = bookService.getBook(bookId).convertToEntity();

        LOGGER.info("Book is found {}", book);

        if(book.getStockCount() <= 0) {
            throw new StockNotEnoughException();
        }

        Order order = new OrderDTO().convertToEntityWithBookIdAndCustomerId(bookId, customerId);

        orderRepository.save(order);

        bookService.update(book);

        LOGGER.info("Order is created: {}", order);

    }

    public OrderDTO getOrder(String orderId, String customerId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if(!order.isPresent()) {
            throw new NotFoundException(NOT_FOUND_ORDER, orderId);
        }

        if(!order.get().getCustomerId().equals(customerId)) {
            throw new OrderAccessException();
        }

        Order foundedOrder = order.get();
        return new OrderDTO(foundedOrder.getBookId(), foundedOrder.getId(), foundedOrder.getCustomerId(), foundedOrder.getStatus(), foundedOrder.getOrderDate());
    }

    public List<OrderDTO> getOrders(String customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        return convertEntitiesToDTOS(orders);

    }

    private List<OrderDTO> convertEntitiesToDTOS(List<Order> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setBookId(order.getBookId());
            orderDTO.setCustomerId(order.getCustomerId());
            orderDTO.setOrderId(order.getId());
            orderDTO.setOrderStatus(order.getStatus());
            orderDTO.setOrderDate(order.getOrderDate());

            orderDTOS.add(orderDTO);
        }

        return orderDTOS;
    }

}
