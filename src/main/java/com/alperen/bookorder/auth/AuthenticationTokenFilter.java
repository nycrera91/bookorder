package com.alperen.bookorder.auth;

import com.alperen.bookorder.exception.AuthFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends GenericFilterBean {

    private AuthService authService;

    public AuthenticationTokenFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            Authentication userAuthentication = authService.getUserAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthFailedException e) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentication header not valid.");
        }
    }
}
