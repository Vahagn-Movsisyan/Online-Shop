package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.User;
import com.example.onlineshope.entity.UserRole;
import com.example.onlineshope.exceptions.EmailIsPresentException;
import com.example.onlineshope.exceptions.PasswordNotMuchException;
import com.example.onlineshope.repository.UserRepository;
import com.example.onlineshope.service.UserService;
import com.example.onlineshope.util.PictureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendMessageService sendMessageService;

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

        PictureUtil.processImageUploadUser(user, multipartFile, uploadDirectory);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);

        user.setEmailConfirmCode(generateEmailConfirmationCode());
        user.setActive(false);

        User register = userRepository.save(user);
        sendMessageService.sendEmailConfirmMail(user);
        return register;
    }

    @Override
    public User save(User user) {
         return userRepository.save(user);
    }

    @Override
    public User update(User user, MultipartFile multipartFile) throws IOException {
        PictureUtil.processImageUploadUser(user, multipartFile, uploadDirectory);
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

    @Override
    public Optional<User> findUserByEmailConfirmCode(String emailConfirmCode) {
        return userRepository.findUserByEmailConfirmCode(emailConfirmCode);
    }

    private String generateEmailConfirmationCode() {
        return  Integer.toString(ThreadLocalRandom.current().nextInt(100000, 999999));
    }
}
