package com.group5.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "describes")
    private String describe;

    @Column(name = "images")
    private String image;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;
}
