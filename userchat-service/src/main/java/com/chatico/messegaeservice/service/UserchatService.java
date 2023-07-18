package com.chatico.messegaeservice.service;

import com.chatico.messegaeservice.client.MessagesClient;
import com.chatico.messegaeservice.domain.UserChat;
import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.dto.UserChatDto;
import com.chatico.messegaeservice.repositiry.UserChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserchatService {
    private final UserChatRepository userChatRepository;
    private final MessagesClient messagesClient;

    public UserChatDto getWithMessagesById(Long userchatId) {

        //call messages service and load messages
        //url = {messagesadress}/messages?userchatId
//        List<ServiceInstance> messagesInstances = discoveryClient.getInstances("messages");
//        ServiceInstance instance = messagesInstances.get(0);
        List<MessageDto> messagesByUsechatId = messagesClient.getMessagesByUserchatId(userchatId);


        //create userchatDTO
        UserChat userChat = userChatRepository.findById(userchatId).orElseThrow();
        return new UserChatDto(userChat.getId(),userChat.getName(), messagesByUsechatId);
    }
}
