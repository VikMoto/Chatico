package com.chatico.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class GoogleAuthProvider {
    private final UserService userService;
    private final GoogleAuthClient googleAuthClient;

    public GoogleAuthProvider(UserService userService, GoogleAuthClient googleAuthClient) {
        this.userService = userService;
        this.googleAuthClient = googleAuthClient;
    }

    public User registerUser(String googleIdToken) {
        // Отримання інформації про користувача з Google
        GoogleUserInfo googleUserInfo = googleAuthClient.getUserInfo(googleIdToken);

        // Перевірка, чи користувач з Google ID вже існує в системі
        if (userService.isUserExistsByGoogleId(googleUserInfo.getId())) {
            throw new RuntimeException("Користувач з Google ID вже зареєстрований");
        }

        // Створення нового користувача
        User user = new User();
        user.setGoogleId(googleUserInfo.getId());
        user.setEmail(googleUserInfo.getEmail());
        user.setName(googleUserInfo.getName());

        // Збереження користувача в базі даних
        return userService.saveUser(user);
    }

    public User loginUser(String googleIdToken) {
        // Отримання інформації про користувача з Google
        GoogleUserInfo googleUserInfo = googleAuthClient.getUserInfo(googleIdToken);

        // Перевірка, чи користувач з Google ID вже існує в системі
        User user = userService.getUserByGoogleId(googleUserInfo.getId());
        if (user != null) {
            return user;
        }

        throw new RuntimeException("Користувач не знайдений");
    }
}
