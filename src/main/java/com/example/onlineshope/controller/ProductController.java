package com.example.onlineshope.controller;

import com.example.onlineshope.entity.Product;
import com.example.onlineshope.security.SpringUser;
import com.example.onlineshope.service.CategoryService;
import com.example.onlineshope.service.ProductPictureService;
import com.example.onlineshope.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductPictureService productPictureService;

    @GetMapping("/add/page")
    public String productAddPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "admin/addProduct";
    }

    @GetMapping("/list")
    public String productList(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.findAll());
        return "admin/productList";
    }

    @GetMapping("/single/page/{id}")
    public String singlePage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isPresent()) {
            modelMap.addAttribute("product", byId.get());
            return "user/singleProductPage";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, @AuthenticationPrincipal SpringUser springUser) {
        Optional<Product> byId = productService.findById(id);

        byId.ifPresent(product -> {
            log.info("product with id {} was delete {} with id {} and email {}",
                    product.getId(),
                    springUser.getUser().getUserRole(),
                    springUser.getUser().getId(),
                    springUser.getUser().getEmail());
            productService.deleteById(id);
        });
        return "redirect:/product/list";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("pics") List<MultipartFile> pics,
                             @AuthenticationPrincipal SpringUser springUser) {
        product.setUser(springUser.getUser());
        productPictureService.saveAll(productService.save(product), pics);

        log.info("product with name {} was add {} with id {} and email {}",
                product.getName(),
                springUser.getUser().getUserRole(),
                springUser.getUser().getId(),
                springUser.getUser().getEmail());
        return "redirect:/product/list";
    }
}
