package com.chatico.messegaeservice.client;



import com.chatico.messegaeservice.dto.UserChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserchatServiceClient {
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public UserChatDto getById(Long userchatId) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("userchats");
        ServiceInstance userchatsInstances = discoveryClient.getInstances("userchats").get(0);
//        Map<String, String> metadata = userchatsInstances.getMetadata();
//        for (Map.Entry<String, String> entry : metadata.entrySet()) {
//            System.out.println("entry.getValue() = " + entry.getValue());
//        }
        String userchatUrl = userchatsInstances.getUri() + "/users/{userchatId}";
//        System.out.println("userchatUrl = " + userchatUrl);
        //FailFast strategy
        return circuitBreakerFactory.create("userchats")
                .run(() -> restTemplate.getForObject(userchatUrl, UserChatDto.class, userchatId),
                        //FallBack stratagy
                        throwable -> new UserChatDto(userchatId, "HIDDEN"));
        //userchats

    }


}
