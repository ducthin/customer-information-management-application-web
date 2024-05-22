package com.group5.demo.email;

import com.group5.demo.model.Customers;

public interface EmailService {
    void sender(Customers customers);

    void setEnable(String email);
}
