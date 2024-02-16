package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.Category;
import com.example.onlineshope.entity.UserRole;
import com.example.onlineshope.exceptions.IdNotFoundException;
import com.example.onlineshope.repository.CategoryRepository;
import com.example.onlineshope.security.SpringUser;
import com.example.onlineshope.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category, SpringUser springUser) {
        if (springUser.getUser().getUserRole() == UserRole.ADMIN) {
            category.setUser(springUser.getUser());
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        byId.ifPresent(category -> {
            categoryRepository.deleteById(id);
        });
        byId.orElseThrow(IdNotFoundException::new);
    }
}
