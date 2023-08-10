package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.Message;
import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import com.chatico.messegaeservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;
    private final MessageService  messageService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return "Hello from Messages services";
    }

    @GetMapping("/testUserChat")
    public String testUserChat() {
        return restTemplate.getForObject("http://userchats/test", String.class);
    }

    @GetMapping("/list")
    public List<Message> list() {
        return new ArrayList<>(messageRepository.findAll()).stream()
                .sorted(Comparator.comparing(Message::getCreationDate).reversed())
                .toList();
    }

    @GetMapping("/users")
    public List<Message> getMessagesByUserchatId(@RequestParam Long userchatId) {
        return messageRepository.findAllByUserchatId(userchatId);
    }

    @PostMapping("/{userchatId}")
    public Message createUserChat(@PathVariable("userchatId")  Long userchatId, @RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        message.setUserchatId(userchatId);
        return messageRepository.save(message);
    }

    @GetMapping("/chats/{chatId}/{id}")
    public List<MessageDto> getMessagesByGroupChatId(@PathVariable("chatId")  Long chatId, @PathVariable("id")  Long id) {

        return messageService.getMessagesByGroupChatId(chatId, id);
//                .stream()
//                .sorted(Comparator.comparing(MessageDto::getCreationDate).reversed())
//                .map(this::convertToDto)
//                .collect(Collectors.toList());

    }

    @GetMapping("/chats/{id}")
    public List<MessageDto> getMessagesWithId(@PathVariable("id")  Long id) {

        return messageService.getMessagesByUserchatId(id);
//                .stream()
//                .sorted(Comparator.comparing(MessageDto::getCreationDate).reversed())
//                .map(this::convertToDto)
//                .collect(Collectors.toList());

    }

}
