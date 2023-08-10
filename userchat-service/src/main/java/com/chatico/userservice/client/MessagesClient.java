package com.chatico.userservice.client;

import com.chatico.userservice.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "messages")
public interface MessagesClient {
    @GetMapping("/messages/list")
    List<MessageDto> getAllMessages();

    @GetMapping("/messages/userchat")
    public List<MessageDto> getMessagesByUserchatId(@RequestParam("userchatId") Long userchatId);
}
