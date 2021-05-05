package com.alperen.bookorder.controller;

import com.alperen.bookorder.dto.OrderDTO;
import com.alperen.bookorder.model.CustomerAuthentication;
import com.alperen.bookorder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@AuthenticationPrincipal CustomerAuthentication customerAuthentication, @RequestParam String bookId) {
        orderService.saveOrder(bookId, customerAuthentication.getDetails().getId());
    }

    @GetMapping("{orderId}")
    public void getOrder(@AuthenticationPrincipal CustomerAuthentication customerAuthentication, @PathVariable String orderId) {
        orderService.getOrder(orderId, customerAuthentication.getDetails().getId());
    }

    @GetMapping
    public List<OrderDTO> getOrders(@AuthenticationPrincipal CustomerAuthentication customerAuthentication) {
        return orderService.getOrders(customerAuthentication.getDetails().getId());
    }
}
