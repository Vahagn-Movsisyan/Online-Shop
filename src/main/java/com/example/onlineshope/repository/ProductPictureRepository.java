package com.example.onlineshope.repository;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {
    List<ProductPicture> findAllByProduct(Product product);
}
