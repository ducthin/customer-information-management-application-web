package com.group5.demo.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequestProduct {
    private String name;

    private String describe;

    private String image;

    private int price;
}
