package com.ecommerce.API.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional
    @Query(value = "select create_customer(:firstName, :lastName, :email, :telephone, :password, :defaultAddressId)", nativeQuery = true)
    boolean createCustomer(String firstName, String lastName, String email, String telephone, String password, Long defaultAddressId);

    @Transactional
    @Query(value = "select check_password(:email, :password)", nativeQuery = true)
    boolean authenticateCustomer(String email, String password);

    Customer findByEmail(String email);
}
