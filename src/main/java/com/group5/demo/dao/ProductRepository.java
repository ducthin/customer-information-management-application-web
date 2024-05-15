package com.group5.demo.dao;

import com.group5.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findByName(String name);
    public void deleteByName(String name);
    public Product findById(int id);
//    public List<Product> findById(int id);
    public List<Product> findProductByCustomersId(int id);
}
