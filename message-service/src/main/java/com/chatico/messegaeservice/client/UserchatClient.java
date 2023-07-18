package com.chatico.messegaeservice.client;

import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.dto.UserChatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "userchats")
public interface UserchatClient {
    @GetMapping("/users/list")
    List<UserChatDto> getAllUserchat();

    @GetMapping("/users/{id}")
    UserChatDto getUserchatById(@PathVariable("id") Long id);
}
