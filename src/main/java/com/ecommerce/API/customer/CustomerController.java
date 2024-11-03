package com.ecommerce.API.customer;

import com.ecommerce.API.response.BooleanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/admin/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/api/admin/customer/")
    public ResponseEntity<BooleanResponse> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(new BooleanResponse(customerService.createCustomer(customer)));
    }

    @PostMapping("/api/public/customer/authenticate")
    public ResponseEntity<BooleanResponse> authenticateCustomer(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(new BooleanResponse(customerService.authenticateCustomer(email, password)));
    }
}
