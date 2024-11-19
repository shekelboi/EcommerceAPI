package com.ecommerce.API.security;

import com.ecommerce.API.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

public class CustomerAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerService customerService;
    private UserDetailsService userDetailsService;

    // This should generate a JWT token and store it somewhere
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println(email + "," + password);
        boolean success = customerService.authenticateCustomer(email, password);
        if (!success) {
            return null;
        }
//        authentication.getName()
        return new UsernamePasswordAuthenticationToken(email, password, Collections.singleton(new SimpleGrantedAuthority("USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
