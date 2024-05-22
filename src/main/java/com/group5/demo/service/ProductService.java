package com.group5.demo.service;

import com.group5.demo.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product requestProduct);

    List<Product> getAllProduct();

    Product findProduct(String Street);

    Product findByID(int id);

    List<Product> findByCustomerId(int id);

    void delete(int address);

    boolean isProduct(String street);
}
