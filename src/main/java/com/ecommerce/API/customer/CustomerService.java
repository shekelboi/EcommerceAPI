package com.ecommerce.API.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    boolean createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getTelephone(), customer.getPassword(), customer.getDefaultAddressId());
    }
}
