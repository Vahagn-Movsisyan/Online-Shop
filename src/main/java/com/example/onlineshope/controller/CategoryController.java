package com.example.onlineshope.controller;

import com.example.onlineshope.entity.Category;
import com.example.onlineshope.security.SpringUser;
import com.example.onlineshope.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public String categories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return  "admin/categoryList";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/category/list";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, @AuthenticationPrincipal SpringUser springUser) {
        categoryService.save(category, springUser);
        return "redirect:/category/list";
    }
}
