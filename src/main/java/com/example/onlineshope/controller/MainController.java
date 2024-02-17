package com.example.onlineshope.controller;

import com.example.onlineshope.service.CategoryService;
import com.example.onlineshope.service.ProductService;
import com.example.onlineshope.util.PictureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Value("${picture.upload.directory.product}")
    private String uploadDirectoryProduct;

    @Value("${picture.upload.directory.user}")
    private String uploadDirectoryUser;

    @GetMapping("/")
    public String homePage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("products", productService.findAll());
        return "user/userHome";
    }

    @GetMapping(value = "/getImageProduct", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageProduct(@RequestParam("picName") String picName) throws IOException {
        return PictureUtil.getImage(picName, uploadDirectoryProduct);
    }

    @GetMapping(value = "/getImageUser", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageUser(@RequestParam("picName") String picName) throws IOException {
        return PictureUtil.getImage(picName, uploadDirectoryUser);
    }
}
