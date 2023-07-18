package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.Message;
import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import com.chatico.messegaeservice.service.MessageService;
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
    private final MessageService  messageService;


    @GetMapping("/list")
    public List<Message> list() {
        return new ArrayList<>(messageRepository.findAll());
    }

    @GetMapping("/users")
    public List<Message> getMessagesByUserchatId(@RequestParam Long userchatId) {
        return messageRepository.findAllByUserchatId(userchatId);
    }

    @PostMapping("/{id}")
    public Message createUserChat(@PathVariable("id")  Long id, @RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        message.setUserchatId(id);
        return messageRepository.save(message);
    }

    @GetMapping("/users/{id}")
    public List<Message> getMessagesWithId(@PathVariable("id")  Long id) {
        Long userchatId = id;
        return messageService.getMessagesByUserchatId(userchatId);
    }
}
