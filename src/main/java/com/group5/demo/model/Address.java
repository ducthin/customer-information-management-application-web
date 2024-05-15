package com.group5.demo.model;

import com.group5.demo.model.Customers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tinh;
    private String huyen;
    private String xa;
    private String street;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;

}
