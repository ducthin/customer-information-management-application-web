package com.group5.demo.service;

import com.group5.demo.model.Product;
import com.group5.demo.request.RequestProduct;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product requestProduct);

    public List<Product> getAllProduct();

    public Product findProduct(String Street);
    public Product findByID(int id);

    public List<Product> findByCustomerId(int id);
    public void delete(int address);

    public boolean isProduct(String street);
}
