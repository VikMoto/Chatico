package com.chatico.userservice.security;



import com.chatico.userservice.repositiry.UserChatRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private final UserChatRepository userChatRepository;

    public BeanConfiguration(UserChatRepository userChatRepository) {
        this.userChatRepository = userChatRepository;
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService(){
        return new UserDetailsServiceImpl(userChatRepository);
    }

}
