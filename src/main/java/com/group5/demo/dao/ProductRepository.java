package com.group5.demo.dao;

import com.group5.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    void deleteByName(String name);

    Product findById(int id);

    //    public List<Product> findById(int id);
    List<Product> findProductByCustomersId(int id);
}
