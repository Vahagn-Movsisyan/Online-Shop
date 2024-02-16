package com.example.onlineshope.service;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.entity.ProductPicture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductPictureService {
    ProductPicture save(ProductPicture productPicture);
    List<ProductPicture> findAllByProduct(Product product);
    void saveAll(Product product, List<MultipartFile> pics);
}
