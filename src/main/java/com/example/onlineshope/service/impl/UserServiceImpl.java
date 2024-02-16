package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.User;
import com.example.onlineshope.entity.UserRole;
import com.example.onlineshope.exceptions.EmailIsPresentException;
import com.example.onlineshope.exceptions.PasswordNotMuchException;
import com.example.onlineshope.repository.UserRepository;
import com.example.onlineshope.service.UserService;
import com.example.onlineshope.util.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${picture.upload.directory.user}")
    private String uploadDirectory;

    @Override
    public User register(User user, String confirmPassword,  MultipartFile multipartFile) throws IOException, EmailIsPresentException, PasswordNotMuchException {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new EmailIsPresentException("Email is already in use");
        } else if (!user.getPassword().equals(confirmPassword)) {
            throw new PasswordNotMuchException("Passwords do not match");
        }

        MultipartUtil.processImageUploadUser(user, multipartFile, uploadDirectory);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, MultipartFile multipartFile) throws IOException {
        MultipartUtil.processImageUploadUser(user, multipartFile, uploadDirectory);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
