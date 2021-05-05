package com.alperen.bookorder.dto;

import com.alperen.bookorder.model.Customer;
import org.apache.commons.codec.digest.DigestUtils;

public class CustomerRegisterDTO {

    private String id;

    private String name;

    private String surname;

    private String email;

    private String password;

    public CustomerRegisterDTO() {
    }

    public CustomerRegisterDTO(String id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer convertToEntity() {
        Customer customer = new Customer();

        customer.setName(getName());
        customer.setSurname(getSurname());
        customer.setEmail(getEmail());
        customer.setPassword(DigestUtils.md5Hex(getPassword()));

        return customer;
    }

    @Override
    public String toString() {
        return "CustomerRegisterDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
