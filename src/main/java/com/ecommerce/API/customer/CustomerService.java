package com.ecommerce.API.customer;

import com.ecommerce.API.security.CustomerAuthenticationManager;
import com.ecommerce.API.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public boolean createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getTelephone(), customer.getPassword(), customer.getDefaultAddressId());
    }

    public String login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(email);
        }
        return "Failure!";
    }

    public boolean logout(String token) {
        return jwtService.invalidateToken(token);
    }
}
