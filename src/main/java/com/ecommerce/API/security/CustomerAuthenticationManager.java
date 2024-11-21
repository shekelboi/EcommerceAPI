package com.ecommerce.API.security;

import com.ecommerce.API.customer.CustomerRepository;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomerAuthenticationManager implements AuthenticationManager {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (email == null || password == null) {
            throw new BadCredentialsException("Missing username or password.");
        }
        if (!customerRepository.authenticateCustomer(email, password)) {
            throw new BadCredentialsException("Incorrect credentials.");
        }
        return new UsernamePasswordAuthenticationToken(email, null, Collections.of(new SimpleGrantedAuthority("USER")));
    }
}
