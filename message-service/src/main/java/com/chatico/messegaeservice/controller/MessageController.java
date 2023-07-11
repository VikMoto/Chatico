package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.Message;
import com.chatico.messegaeservice.domain.UserChat;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import com.chatico.messegaeservice.repositiry.UserChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final UserChatRepository userChatRepository;
    private final MessageRepository messageRepository;

    @GetMapping("/list")
    public List<Message> list() {
        return new ArrayList<>(messageRepository.findAll());
    }

    @PostMapping
    public Message createUserChat(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
