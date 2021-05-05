package com.alperen.bookorder.controller;

import com.alperen.bookorder.dto.CustomerRegisterDTO;
import com.alperen.bookorder.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        LOGGER.info("Customer create request: {}", customerRegisterDTO );

        customerService.saveCustomer(customerRegisterDTO);

    }


}
