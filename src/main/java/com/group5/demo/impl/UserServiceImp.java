package com.group5.demo.impl;

import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.model.Customers;
import com.group5.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found user"));
    }

    public Customers saveCustomer(String username){
        UserDetails userDetails = loadUserByUsername(username);
        Customers customers = customerService.findByEmail(userDetails.getUsername());
        return customers;
    }
}
