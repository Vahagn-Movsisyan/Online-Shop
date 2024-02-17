package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.entity.ProductPicture;
import com.example.onlineshope.repository.ProductPictureRepository;
import com.example.onlineshope.service.ProductPictureService;
import com.example.onlineshope.util.PictureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPictureServiceImpl implements ProductPictureService {

    private final ProductPictureRepository productPictureRepository;

    @Value("${picture.upload.directory.product}")
    private String uploadDirectory;

    @Override
    public List<ProductPicture> findByProductId(int id) {
        return productPictureRepository.findByProductId(id);
    }

    @Override
    public void saveAll(Product product, List<MultipartFile> multipartFiles) {
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            for (MultipartFile multipartFile : multipartFiles) {
                String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                File file = new File(uploadDirectory, picName);
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                save(ProductPicture.builder()
                        .product(product)
                        .picName(picName)
                        .build());
            }
        }
    }

    @Override
    public void deleteAllByProductId(int id) {
        productPictureRepository.deleteAllByProductId(id);
    }

    @Override
    public ProductPicture save(ProductPicture productPicture) {
        return productPictureRepository.save(productPicture);
    }

    @Override
    public List<ProductPicture> findAllByProduct(Product product) {
        return productPictureRepository.findAllByProduct(product);
    }
}
