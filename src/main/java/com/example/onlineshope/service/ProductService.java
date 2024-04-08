package com.example.onlineshope.service;

import com.example.onlineshope.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(int id);

    Page<Product> findAll(Pageable pageable);

    List<Product> findProductByCategoryId(int id);

    void deleteById(int id);
}
