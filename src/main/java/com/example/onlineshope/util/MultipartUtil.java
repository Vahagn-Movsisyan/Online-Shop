package com.example.onlineshope.util;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class MultipartUtil {
    public static void processImageUploadUser(User user, MultipartFile multipartFile, String uploadDirectory) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            multipartFile.transferTo(file);
            user.setPicName(picName);
        }
    }
}
