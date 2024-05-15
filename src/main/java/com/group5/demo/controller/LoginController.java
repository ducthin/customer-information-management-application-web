package com.group5.demo.controller;

import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.email.EmailService;
import com.group5.demo.impl.UserServiceImp;
import com.group5.demo.model.Customers;
import com.group5.demo.request.ResRequest;
import com.group5.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/register")
    public String registerpage(Model model)
    {
        model.addAttribute("customers",new Customers());
        return "customers/register_form";
    }
    @PostMapping("/saveRegister")
    public String register(@ModelAttribute("customers") ResRequest request, Model model, RedirectAttributes redirectAttributes)
    {
        Customers customers = customerService.findByEmail(request.getEmail());
        if(customers != null)
        {
            redirectAttributes.addAttribute("fail", true);
            return "redirect:/auth/register";
        }else{
            customerService.save(request);
            redirectAttributes.addAttribute("success", true);
            return "redirect:/auth/loginPage";
        }
    }

    @GetMapping("/loginPage")
    public String showLoginForm(Model model) {
        return "customers/login_form";
    }


    // add request mapping for /access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "customers/access-denied";
    }


    @GetMapping("/home")
    public String home(Model model,Principal principal)
    {
        Customers customers = (Customers) userServiceImp.loadUserByUsername(principal.getName());
        model.addAttribute("customer",customers);
        return "customers/home";
    }

    @PostMapping("/confirm")
    public String confirmEmail(@ModelAttribute("customers")Customers customers, Model model)
    {
        Customers customer = customerRepository.findByOtp(customers.getOtp());
        emailService.setEnable(customer.getEmail());
        model.addAttribute("message","Register success");
        return "customers/confirm_email";
    }
    @GetMapping("/confirm")
    public String get( Model model)
    {
        model.addAttribute("customers",new Customers());
        return "customers/confirm_email";
    }
}
