package com.chatico.messegaeservice.controller;

import com.chatico.messegaeservice.domain.UserChat;
import com.chatico.messegaeservice.dto.UserChatDto;
import com.chatico.messegaeservice.dto.UserchatRegDto;
import com.chatico.messegaeservice.repositiry.UserChatRepository;
import com.chatico.messegaeservice.service.UserchatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserChatController {
    private static int DELAY = 100;
    private final UserChatRepository userChatRepository;
    private final UserchatService userchatService;

    @GetMapping("/list")
    public List<UserChat> list() {
        return new ArrayList<>(userChatRepository.findAll());
    }

    @PostMapping(("/registration"))
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
    public UserChatDto getUserChatWithMessages(@PathVariable("id")  Long id) throws InterruptedException {
        UserChat userChat = userChatRepository.findById(id).orElseThrow();
        UserChatDto userChatDto = UserChatDto.builder()
                .id(userChat.getId())
                .name(userChat.getName())
                .build();
        log.info("waiting {}ms", DELAY);
        Thread.sleep(DELAY += 50);
        log.info("responding with error");
        return userChatDto;
//        throw new RuntimeException("Unexpected error");
    }
}
