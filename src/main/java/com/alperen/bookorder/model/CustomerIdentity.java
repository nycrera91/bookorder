package com.alperen.bookorder.model;

import java.io.Serializable;

public class CustomerIdentity implements Serializable {

    private static final long serialVersionUID = 7459832174334654232L;

    private String id;

    private String name;

    private String surname;

    private String email;

    public CustomerIdentity() {
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

    @Override
    public String toString() {
        return "CustomerIdentity{" +
                "customerId='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
