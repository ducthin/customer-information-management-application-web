package com.group5.demo.controller;

import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.impl.UserServiceImp;
import com.group5.demo.model.Address;
import com.group5.demo.model.Customers;
import com.group5.demo.request.RequestAddress;
import com.group5.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/save")
    public String saveAddress(@ModelAttribute("address") RequestAddress requestAddress, Principal principal) {
        Address address = new Address();
        address.setStreet(requestAddress.getStreet());
        address.setXa(requestAddress.getXa());
        address.setHuyen(requestAddress.getHuyen());
        address.setTinh(requestAddress.getTinh());

        Customers customers = userServiceImp.saveCustomer(principal.getName());
        address.setCustomers(customers);
        Address address1 = addressService.saveAddress(address);
        customers.setAddress(address1);
        customerRepository.save(customers);
        return "redirect:/api/product/findAllProduct";
    }

    @GetMapping("/save")
    public String saveAddressCustomer(Model model) {
        model.addAttribute("address", new Address());
        return "customers/add_address";
    }

    @PostMapping("/delete")
    public String deleteAddress(@RequestParam("addressId") int id, Model model) {
        addressService.delete(id);
        model.addAttribute("message", "Delete Success");
        return "customers/deleteAddress";
    }

    @GetMapping("/findAll")
    public String findAllAddress(Model model) {
        List<Address> addresses = addressService.getAllAddress();
        model.addAttribute("listAddress", addresses);
        return "customer/findAddress";
    }

    @GetMapping("/find")
    public String findAddress(@RequestParam("addressStreet") String id, Model model) {
        Address address = addressService.findAddress(id);
        model.addAttribute("address", address);
        return "customers/";
    }


    @PostMapping("/update")
    public String updateAddress(@ModelAttribute("address") RequestAddress requestAddress, Model model, @RequestParam("addressStreet") String street) {
        Address address1 = addressService.findAddress(street);
        Address address = new Address();
        address.setStreet(requestAddress.getStreet());
        address.setXa(requestAddress.getXa());
        address.setHuyen(requestAddress.getHuyen());
        address.setHuyen(requestAddress.getTinh());
        addressService.saveAddress(address);
        return "customers/update";
    }
}
