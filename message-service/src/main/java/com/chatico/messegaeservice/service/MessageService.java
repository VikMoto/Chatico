package com.chatico.messegaeservice.service;

import com.chatico.messegaeservice.client.UserchatClient;
import com.chatico.messegaeservice.domain.Message;

import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.dto.UserChatDto;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserchatClient userchatClient;

    public List<MessageDto> getMessagesByUserchatId(Long userchatId) {

        //call userchat service and load userchat
        //url = {userchatadress}/userchat/{id}
        UserChatDto userchat = userchatClient.getUserchatById(userchatId);
        System.out.println("userchat = " + userchat);
        List<MessageDto> messageDtos = new ArrayList<>();

//        //create messageDTO
        List<Message> messages = messageRepository.findAllById(Collections.singletonList(userchatId));
        for (Message message : messages) {
            messageDtos.add(new MessageDto(userchat.id(),userchat.name(), messages));
        }
        return messageDtos;

    }
}
