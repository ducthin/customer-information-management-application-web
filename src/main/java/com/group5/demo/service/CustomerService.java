package com.group5.demo.service;

import com.group5.demo.model.Customers;
import com.group5.demo.request.ResRequest;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

public interface CustomerService {
    public List<Customers> findAll();

    public List<Customers> search(String keyword);

    public Customers findById(int theId);

    public void save(ResRequest theCustomer);
    public void deleteById(int theId);
    public void saveAdmin(ResRequest theCustomer);
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(Customers request);

    public boolean findByEmails(String lastname);
    public Customers findByEmail(String email);
    public Customers updateCustomer(int id,ResRequest theCustomer);
}

