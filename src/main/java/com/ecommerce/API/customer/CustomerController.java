package com.ecommerce.API.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/customer/")
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
}
