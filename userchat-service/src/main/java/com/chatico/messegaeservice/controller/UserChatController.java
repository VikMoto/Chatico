package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.UserChat;
import com.chatico.messegaeservice.dto.UserChatDto;
import com.chatico.messegaeservice.dto.UserchatRegDto;
import com.chatico.messegaeservice.repositiry.UserChatRepository;
import com.chatico.messegaeservice.service.UserchatService;
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
    private final UserchatService userchatService;

    @GetMapping("/list")
    public List<UserChat> list() {
        return new ArrayList<>(userChatRepository.findAll());
    }

    @PostMapping
    public UserChat createUserChat(@RequestBody UserchatRegDto userchatRegDto) {
        UserChat userChat = UserChat.builder()
                .name(userchatRegDto.getName())
                .email(userchatRegDto.getEmail())
                .userPic(userchatRegDto.getUserPic())
                .gender(userchatRegDto.getGender())
                .birthday(userchatRegDto.getBirthday())
                .role(userchatRegDto.getRole())
                .locale(null)
                .lastVisit(LocalDateTime.now())
                .build();
        return userChatRepository.save(userChat);
    }

    @GetMapping("/{id}")
    public UserChatDto getUserChatWithMessages(@PathVariable  Long id) {
       return userchatService.getWithMessagesById(id);
    }
}
