package com.chatico.messegaeservice.congiguration;

import com.chatico.messegaeservice.domain.GroupChat;
import com.chatico.messegaeservice.domain.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BeanConfiguration {

    @Bean
    @DependsOn("messageBean")
    public GroupChat groupChatBean() {
        return new GroupChat();
    }

    @Bean
    public Message messageBean() {
       return new Message();
    }
}


