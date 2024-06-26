package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.entity.ProductPicture;
import com.example.onlineshope.repository.ProductRepository;
import com.example.onlineshope.service.ProductPictureService;
import com.example.onlineshope.service.ProductService;
import com.example.onlineshope.util.PictureUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductPictureService productPictureService;

    @Value("${picture.upload.directory.product}")
    private String uploadDirectory;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findProductByCategoryId(int id) {
        return productRepository.findProductByCategoryId(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        byId.ifPresent(product -> {

            for (ProductPicture productPicture : product.getProductPictures()) {
                PictureUtil.deletePicture(uploadDirectory, productPicture.getPicName());
            }

            productPictureService.deleteAllByProductId(id);
            productRepository.deleteById(id);
        });
    }
}
