package com.group5.demo.impl;

import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.email.EmailService;
import com.group5.demo.model.Customers;
import com.group5.demo.request.ResRequest;
import com.group5.demo.role.Role;
import com.group5.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    public CustomerServiceImpl(CustomerRepository theCustomerRepository) {

        customerRepository = theCustomerRepository;
    }
    @Override
    public List<Customers> findAll() {

        return customerRepository.findAll();
    }

    @Override
    public List<Customers> search(String keyword) {

        return customerRepository.findByPhoneContaining(keyword);
    }

    @Override
    public Customers findById(int theId) {
        Optional<Customers> result = customerRepository.findById(theId);

        Customers customer = null;

        if (result.isPresent()) {
            customer = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return customer;
    }

    @Override
    public void save(ResRequest theCustomer) {
        Customers customer = new Customers();
        customer.setEmail(theCustomer.getEmail());
        customer.setPassword(passwordEncoder.encode(theCustomer.getPassword()));
        customer.setFirstName(theCustomer.getFirstName());
        customer.setLastName(theCustomer.getLastName());
        customer.setPhone(theCustomer.getPhone());
        customer.setRole(Role.USER);
        customer.setEnable(true);

        customerRepository.save(customer);


    }


    @Override
    public void saveAdmin(ResRequest theCustomer) {
        Customers customer = new Customers();
        customer.setEmail(theCustomer.getEmail());
        customer.setPassword(passwordEncoder.encode(theCustomer.getPassword()));
        customer.setFirstName(theCustomer.getFirstName());
        customer.setLastName(theCustomer.getLastName());
        customer.setPhone(theCustomer.getPhone());
        customer.setRole(Role.USER);
        customer.setEnable(true);

        customerRepository.save(customer);

    }

    @Override
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }

    @Override
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(Customers request) {
        UserDetails userDetails = User.builder()
                .username(request.getEmail())
                .password(request.getPassword())
                .roles(String.valueOf(request.getRole()))
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Override
    public boolean findByEmails(String lastname) {
        Customers customers= findByEmail(lastname);
        if(customers != null)
        {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Customers findByEmail(String email) {
        Optional<Customers> customersEmail = customerRepository.findByEmail(email);
        Customers customers = null;
        if(customersEmail.isPresent())
        {
            customers = customersEmail.get();
        }
        return customers;
    }

    @Override
    public Customers updateCustomer(int id,ResRequest theCustomer) {
        Customers customer = findById(id);
        customer.setFirstName(theCustomer.getFirstName());
        customer.setLastName(theCustomer.getLastName());
        customer.setPhone(theCustomer.getPhone());
        customer.setPassword(passwordEncoder.encode(theCustomer.getPassword()));
        return customerRepository.save(customer);
    }


    private String emailSender(String name,String link)
    {
        return "Hi "+name+ " vui long xac nhan email cua ban: " + link;
    }
    private int RandomOtp()
    {
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }
}
