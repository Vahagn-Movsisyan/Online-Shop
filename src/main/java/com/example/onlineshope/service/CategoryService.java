package com.example.onlineshope.service;

import com.example.onlineshope.entity.Category;
import com.example.onlineshope.security.SpringUser;

import java.util.List;

public interface CategoryService {

    Category save(Category category, SpringUser springUser);

    List<Category> findAll();

    void deleteById(int id);
}
