package com.group5.demo.controller;

import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.model.Customers;
import com.group5.demo.request.ResRequest;
import com.group5.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/updateInformation")
    public String updateInformation(@RequestParam("customerEmail") String Email
            , Model model, @ModelAttribute("customers") ResRequest request) {
        Customers customer = customerService.findByEmail(Email);
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        model.addAttribute("customers", customer);
        customerRepository.save(customer);
        return "customers/update_customer";
    }

    @GetMapping("updateInformation")
    public String updateInformationCustomer(Model model) {
        model.addAttribute("customers", new Customers());
        return "customers/update_customer";
    }
}