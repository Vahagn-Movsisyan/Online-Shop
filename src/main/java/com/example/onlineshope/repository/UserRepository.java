package com.example.onlineshope.repository;

import com.example.onlineshope.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    List<User> findUserByActive(boolean isActive);
    Optional<User> findByEmail(String email);
    Optional<User> findUserByEmailConfirmCode(String emailConfirmCode);
}
