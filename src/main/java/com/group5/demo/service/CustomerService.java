package com.group5.demo.service;

import com.group5.demo.model.Customers;
import com.group5.demo.request.ResRequest;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

public interface CustomerService {
    List<Customers> findAll();

    Customers findById(int theId);

    void save(ResRequest theCustomer);

    void deleteById(int theId);

    void saveAdmin(ResRequest theCustomer);

    InMemoryUserDetailsManager inMemoryUserDetailsManager(Customers request);

    boolean findByEmails(String lastname);

    Customers findByEmail(String email);

    Customers updateCustomer(int id, ResRequest theCustomer);

    int RandomOtp();
}

