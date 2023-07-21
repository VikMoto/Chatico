package com.chatico.messegaeservice.client;

import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.dto.UserChatDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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
    @CircuitBreaker(name = "userchats", fallbackMethod = "defaultUserchatDto")
//    @TimeLimiter(name = )
    UserChatDto getById(@PathVariable("id") Long id);
    default  UserChatDto defaultUserchatDto(Long userchatId, Throwable throwable) {
        return new UserChatDto(userchatId, "HIDDEN");
    }
}
