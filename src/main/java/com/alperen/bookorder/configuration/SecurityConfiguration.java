package com.alperen.bookorder.configuration;

import com.alperen.bookorder.auth.AuthService;
import com.alperen.bookorder.auth.AuthenticationTokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthService authService;

    public SecurityConfiguration(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/orders/**")
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(authService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
