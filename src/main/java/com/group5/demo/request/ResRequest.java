package com.group5.demo.request;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

@Data
public class ResRequest {
    private String firstName;
    private String lastName;
    private String birth;
    private String phone;
    private String email;
    private String password;
    private int otp;
//    private String address;
}
