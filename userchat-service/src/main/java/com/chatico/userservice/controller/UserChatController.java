package com.chatico.userservice.controller;

import com.chatico.userservice.domain.UserChat;
import com.chatico.userservice.dto.UserChatDto;
import com.chatico.userservice.dto.UserchatRegDto;
import com.chatico.userservice.repositiry.UserChatRepository;
import com.chatico.userservice.security.AuthenticationType;
import com.chatico.userservice.service.UserchatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserChatController {

    private final UserChatRepository userChatRepository;
    private final UserchatService userchatService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return "Hello from UserChat services";
    }


    @GetMapping("/testMessages")
    public String testMessages() {
        return restTemplate.getForObject("http://messages/test", String.class);
    }


    @GetMapping("/list")
    public List<UserChat> list() {
        return new ArrayList<>((Collection) userChatRepository.findAll());
    }

    @PostMapping(("/registration"))
    public UserChat createUserChat(@RequestBody UserchatRegDto userchatRegDto) {
        UserChat userChat = UserChat.builder()
                .username(userchatRegDto.getUsername())
                .email(userchatRegDto.getEmail())
                .userPic(userchatRegDto.getUserPic())
                .gender(userchatRegDto.getGender())
                .enabled(true)
                .birthday(userchatRegDto.getBirthday())
                .authType(AuthenticationType.DATABASE)
                .roles(userchatRegDto.getRoles())
                .locale(null)
                .lastVisit(LocalDateTime.now())
                .build();
        return userChatRepository.save(userChat);
    }

//    @GetMapping("/{id}")
//    public UserChatDto getUserChatWithMessages(@PathVariable("id")  Long id) throws InterruptedException {
//        UserChat userChat = userChatRepository.findById(id).orElseThrow();
//        UserChatDto userChatDto = UserChatDto.builder()
//                .id(userChat.getId())
//                .name(userChat.getName())
//                .build();
//        log.info("waiting {}ms", DELAY);
//
//        log.info("responding with error");
//        return userChatDto;
////        throw new RuntimeException("Unexpected error");
//    }
}
