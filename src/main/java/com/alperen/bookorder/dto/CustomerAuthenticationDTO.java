package com.alperen.bookorder.dto;

public class CustomerAuthenticationDTO {

    private String email;

    private String password;

    private String jwtToken;

    private String id;

    private String name;

    private String surname;

    public CustomerAuthenticationDTO() {
    }

    public CustomerAuthenticationDTO(String email, String jwtToken, String id, String name, String surname) {
        this.email = email;
        this.jwtToken = jwtToken;
        this.id = id;
        this.name = name;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
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

    @Override
    public String toString() {
        return "CustomerAuthenticationDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                ", customerId='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
