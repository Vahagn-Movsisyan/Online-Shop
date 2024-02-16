package com.example.onlineshope.repository;

import com.example.onlineshope.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int id);

    List<Product> findProductByCategoryId(int id);
}
