package com.chatico.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class EmailAuthProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public EmailAuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password) {
        // Перевірка, чи користувач з вказаним email вже існує в системі
        if (userService.isUserExists(email)) {
            throw new RuntimeException("Користувач з таким email вже зареєстрований");
        }

        // Створення нового користувача
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        // Збереження користувача в базі даних
        return userService.saveUser(user);
    }

    public User loginUser(String email, String password) {
        // Отримання користувача за email
        User user = userService.getUserByEmail(email);

        // Перевірка пароля
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new RuntimeException("Неправильні дані для входу");
    }
}
