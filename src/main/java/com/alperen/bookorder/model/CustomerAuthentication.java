package com.alperen.bookorder.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CustomerAuthentication implements Authentication {


    private static final long serialVersionUID = 2839402738175347852L;

    private final CustomerIdentity customerIdentity;

    private boolean auth = true;

    public CustomerAuthentication(CustomerIdentity customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public CustomerIdentity getDetails() {
        return customerIdentity;
    }

    @Override
    public Object getPrincipal() {
        return customerIdentity.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.auth = b;
    }

    @Override
    public String getName() {
        return customerIdentity.getName();
    }
}
