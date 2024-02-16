package com.example.onlineshope.service;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.security.SpringUser;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(int id);
    List<Product> findAll();

    List<Product> findProductByCategoryId(int id);

    void deleteById(int id);
}
