package com.group5.demo.controller;

import com.group5.demo.dao.ProductRepository;
import com.group5.demo.impl.UserServiceImp;
import com.group5.demo.model.Customers;
import com.group5.demo.model.Product;
import com.group5.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/findAllProduct")
    public String findAllProduct(Model model, Principal principal) {
        Customers customers = (Customers) userServiceImp.loadUserByUsername(principal.getName());
        model.addAttribute("customer", customers);

        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "customers/purchaser_product";
    }

    @GetMapping("/find")
    public String findProduct(@RequestParam("productName") String name, Model model) {
        Product product = productService.findProduct(name);
        model.addAttribute("product", product);
        return "customers/";
    }

    @GetMapping("/purchaser/{id}")
    public String orderProduct(@PathVariable int id, Principal principal) {
        Product product = productService.findByID(id);
        Customers customers = userServiceImp.saveCustomer(principal.getName());

        product.setCustomers(customers);
        productRepository.save(product);
        return "redirect:/api/address/save";
    }

}
