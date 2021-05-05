package com.alperen.bookorder.repository;

import com.alperen.bookorder.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    Optional<Customer> findByEmailAndPassword(String email, String password);

}
