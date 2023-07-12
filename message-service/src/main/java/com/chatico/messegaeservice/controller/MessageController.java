package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.Message;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @GetMapping("/list")
    public List<Message> list() {
        return new ArrayList<>(messageRepository.findAll());
    }

    @GetMapping("/userchat/")
    public List<Message> getMessageById(@RequestParam Long userchatId) {
        return messageRepository.findAllByUserchatId(userchatId);
    }

    @PostMapping
    public Message createUserChat(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
