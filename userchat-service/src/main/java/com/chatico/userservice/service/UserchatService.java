package com.chatico.userservice.service;

import com.chatico.userservice.client.MessagesClient;
import com.chatico.userservice.domain.UserChat;
import com.chatico.userservice.dto.MessageDto;
import com.chatico.userservice.dto.UserChatDto;
import com.chatico.userservice.repositiry.UserChatRepository;
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
        return new UserChatDto(userChat.getId(),userChat.getUsername(), userChat.getUserPic(), userChat.getLastVisit());
    }
}
