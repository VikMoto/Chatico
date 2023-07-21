package com.chatico.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class AppleIDAuthProvider {
    private final UserService userService;
    private final AppleIDAuthClient appleIDAuthClient;

    public AppleIDAuthProvider(UserService userService, AppleIDAuthClient appleIDAuthClient) {
        this.userService = userService;
        this.appleIDAuthClient = appleIDAuthClient;
    }

    public User registerUser(String appleIDAuthorizationCode) {
        // Отримання інформації про користувача з Apple
        AppleIDUserInfo appleIDUserInfo = appleIDAuthClient.getUserInfo(appleIDAuthorizationCode);

        // Перевірка, чи користувач з Apple ID вже існує в системі
        if (userService.isUserExistsByAppleID(appleIDUserInfo.getId())) {
            throw new RuntimeException("Користувач з Apple ID вже зареєстрований");
        }

        // Створення нового користувача
        User user = new User();
        user.setAppleID(appleIDUserInfo.getId());
        user.setEmail(appleIDUserInfo.getEmail());
        user.setName(appleIDUserInfo.getName());

        // Збереження користувача в базі даних
        return userService.saveUser(user);
    }

    public User loginUser(String appleIDAuthorizationCode) {
        // Отримання інформації про користувача з Apple
        AppleIDUserInfo appleIDUserInfo = appleIDAuthClient.getUserInfo(appleIDAuthorizationCode);

        // Перевірка, чи користувач з Apple ID вже існує в системі
        User user = userService.getUserByAppleID(appleIDUserInfo.getId());
        if (user != null) {
            return user;
        }

        throw new RuntimeException("Користувач не знайдений");
    }
}
