package com.chatico.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class FacebookAuthProvider {
    private final UserService userService;
    private final FacebookAuthClient facebookAuthClient;

    public FacebookAuthProvider(UserService userService, FacebookAuthClient facebookAuthClient) {
        this.userService = userService;
        this.facebookAuthClient = facebookAuthClient;
    }

    public User registerUser(String facebookAccessToken) {
        // Отримання інформації про користувача з Facebook
        FacebookUserInfo facebookUserInfo = facebookAuthClient.getUserInfo(facebookAccessToken);

        // Перевірка, чи користувач з Facebook ID вже існує в системі
        if (userService.isUserExistsByFacebookId(facebookUserInfo.getId())) {
            throw new RuntimeException("Користувач з Facebook ID вже зареєстрований");
        }

        // Створення нового користувача
        User user = new User();
        user.setFacebookId(facebookUserInfo.getId());
        user.setEmail(facebookUserInfo.getEmail());
        user.setName(facebookUserInfo.getName());

        // Збереження користувача в базі даних
        return userService.saveUser(user);
    }

    public User loginUser(String facebookAccessToken) {
        // Отримання інформації про користувача з Facebook
        FacebookUserInfo facebookUserInfo = facebookAuthClient.getUserInfo(facebookAccessToken);

        // Перевірка, чи користувач з Facebook ID вже існує в системі
        User user = userService.getUserByFacebookId(facebookUserInfo.getId());
        if (user != null) {
            return user;
        }

        throw new RuntimeException("Користувач не знайдений");
    }
}
