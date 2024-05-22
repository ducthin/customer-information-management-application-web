package com.group5.demo.email;

import com.group5.demo.dao.CustomerRepository;
import com.group5.demo.model.Customers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImp implements EmailService {
    @Autowired
    private CustomerRepository customerRepository;
    private JavaMailSender javaMailSender;

    @Override
    public void sender(Customers customers) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customers.getEmail());
        message.setFrom("lenguyento2k4@gmail.com");
        message.setText(emailSender(customers.getLastName(), customers.getOtp()));
        message.setSubject("Validate Email Please");
        javaMailSender.send(message);
    }

    @Override
    public void setEnable(String email) {
        customerRepository.enableCustomer(email);
    }

    private String emailSender(String name, int link) {
        return "Hi " + name + " vui long xac nhan email cua ban: " + link;
    }
}
