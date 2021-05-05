package com.alperen.bookorder.service;

import com.alperen.bookorder.dto.CustomerRegisterDTO;
import com.alperen.bookorder.model.Customer;
import com.alperen.bookorder.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = customerRegisterDTO.convertToEntity();

        Customer savedCustomer = customerRepository.save(customer);

        LOGGER.info("Customer is saved. Customer information: {}", savedCustomer);
    }


}
