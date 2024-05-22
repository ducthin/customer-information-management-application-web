package com.group5.demo.impl;

import com.group5.demo.dao.ProductRepository;
import com.group5.demo.model.Product;
import com.group5.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product requestProduct) {
        return productRepository.save(requestProduct);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(String name) {

        return productRepository.findByName(name);
    }

    @Override
    public Product findByID(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByCustomerId(int id) {
        return productRepository.findProductByCustomersId(id);
    }


    @Override
    public void delete(int name) {
        productRepository.deleteById(name);
    }

    @Override
    public boolean isProduct(String name) {
        Product product = findProduct(name);
        return product != null;
    }
}
