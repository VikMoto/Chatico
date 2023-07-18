package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.Message;
import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import com.chatico.messegaeservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

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
    public List<MessageDto> getMessagesWithId(@PathVariable("id")  Long id) {
        Long userchatId = id;
        return messageService.getMessagesByUserchatId(userchatId);
//                .stream()
//                .sorted(Comparator.comparing(MessageDto::getCreationDate).reversed())
//                .map(this::convertToDto)
//                .collect(Collectors.toList());

    }

    private MessageDto convertToDto(Message message) {
        return MessageDto.builder()
                .text(message.getText())
                .creationDate(message.getCreationDate())
                .build();
    }
}
