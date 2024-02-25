package com.example.onlineshope.service;

import com.example.onlineshope.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    User register(User user, String confirmPassword, MultipartFile multipartFile) throws IOException;
    User save(User user);
    User update(User user, MultipartFile multipartFile) throws IOException;
    User findByEmail(String email);
    Optional<User> findById(int id);
    Optional<User> findUserByEmailConfirmCode(String emailConfirmCode);
}
