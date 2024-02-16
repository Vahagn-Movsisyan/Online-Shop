package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.repository.ProductRepository;
import com.example.onlineshope.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductByCategoryId(int id) {
        return productRepository.findProductByCategoryId(id);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
