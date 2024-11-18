package com.ecommerce.API.security;

import com.ecommerce.API.customer.Customer;
import com.ecommerce.API.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOOKING FOR USERNAME " + username);
        Customer customer = customerRepository.findByEmail(username);
        System.out.println("Found: " + customer);
        if (customer == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User with the username \"" + username + "\" not found.");
        }
        return new CustomerDetails(customer);
    }
}
