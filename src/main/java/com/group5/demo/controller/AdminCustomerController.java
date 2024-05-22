package com.group5.demo.controller;

import com.group5.demo.model.Customers;
import com.group5.demo.model.Product;
import com.group5.demo.request.RequestProduct;
import com.group5.demo.request.ResRequest;
import com.group5.demo.service.AddressService;
import com.group5.demo.service.CustomerService;
import com.group5.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminCustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/createCustomer")
    public String saveCustomer(@ModelAttribute("customers") ResRequest theCustomer, Model theModel) {

        // save the customer
        customerService.saveAdmin(theCustomer);
        theModel.addAttribute("message", "Create Customers Success");
        // use a redirect to prevent duplicate submissions
        return "redirect:/api/admin/showAllCustomers";
    }

    @GetMapping("/createCustomer")
    public String saveAdmin(Model model) {
        model.addAttribute("customers", new Customers());
        return "customers/create_customer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String showFormForUpdate(@PathVariable int id, @ModelAttribute("customers") ResRequest theCustomer, Model theModel) {

        // get the customer from service
        Customers customer = customerService.updateCustomer(id, theCustomer);

        // set customer in the model to populate the form
        theModel.addAttribute("customers", customer);
        theModel.addAttribute("message", "Update Success");
        // send over to our form
        return "redirect:/api/admin/showAllCustomers";
    }

    @GetMapping("/updateCustomer/{id}")
    public String updateCustomer(Model model, @PathVariable int id) {
        model.addAttribute("customers", new Customers());
        model.addAttribute("id", id);
        return "customers/update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id, Model model) {
        customerService.deleteById(id);
        return "redirect:/api/admin/showAllCustomers";
    }

    @PostMapping("/addProduct")
    public String saveProduct(@ModelAttribute("product") RequestProduct requestProduct, Model model) {
        Product product = new Product();

        product.setImage(requestProduct.getImage());
        product.setDescribe(requestProduct.getDescribe());
        product.setPrice(requestProduct.getPrice());
        product.setName(requestProduct.getName());
        productService.saveProduct(product);
        return "redirect:/api/admin/showProduct";
    }

    @GetMapping("/addProduct")
    public String getSaveProduct(Model model) {
        model.addAttribute("product", new Product());
        return "customers/create_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/api/admin/showProduct";
    }

    @GetMapping("/showAllCustomers")
    public String showFormForAdd(Model theModel) {

        List<Customers> customers = customerService.findAll();

        theModel.addAttribute("customers", customers);

        return "customers/list_customer";
    }

    @GetMapping("/showProduct")
    public String showProduct(Model model) {
        List<Product> products = productService.getAllProduct();

        model.addAttribute("products", products);
        return "customers/product_list";
    }

    @GetMapping("/showAddress")
    public String findAllAddress(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("listProduct", products);
        return "customers/orderProduct";
    }
}
