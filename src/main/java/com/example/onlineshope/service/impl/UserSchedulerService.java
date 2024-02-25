package com.example.onlineshope.service.impl;

import com.example.onlineshope.entity.User;
import com.example.onlineshope.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSchedulerService {

    private final UserRepository userRepository;
    private final SendMessageService sendMessageService;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void deleteNoActiveUsers() {
        List<User> inactiveUsers = userRepository.findUserByActive(false);
        for (User user : inactiveUsers) {
            sendMessageService.send(user.getEmail(), "Deleting your account", "Your account was deleted due to non-activation, try again registration");
            userRepository.delete(user);
        }
    }
}
