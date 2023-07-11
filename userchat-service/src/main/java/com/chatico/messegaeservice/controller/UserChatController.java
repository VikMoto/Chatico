package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.UserChat;
import com.chatico.messegaeservice.repositiry.UserChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserChatController {
    private final UserChatRepository userChatRepository;

    @GetMapping("/list")
    public List<UserChat> list() {
        return new ArrayList<>(userChatRepository.findAll());
    }

    @PostMapping
    public UserChat createUserChat(@RequestBody UserChat userChat) {
        userChat.setLastVisit(LocalDateTime.now());
        return userChatRepository.save(userChat);
    }
}
