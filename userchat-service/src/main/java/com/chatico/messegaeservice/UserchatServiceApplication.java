package com.chatico.messegaeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserchatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserchatServiceApplication.class, args);
    }

}
