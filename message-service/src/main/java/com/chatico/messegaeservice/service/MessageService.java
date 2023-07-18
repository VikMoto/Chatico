package com.chatico.messegaeservice.service;

import com.chatico.messegaeservice.client.UserchatClient;
import com.chatico.messegaeservice.domain.GroupChat;
import com.chatico.messegaeservice.domain.Message;

import com.chatico.messegaeservice.domain.PrivatChat;
import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.dto.UserChatDto;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final MessageRepository messageRepository;
    private final UserchatClient userchatClient;

    public List<Message> getMessagesByUserchatId(Long userchatId) {
        String query = "SELECT m.id, m.text, m.userchat_id, m.creation_date FROM message m WHERE userchat_id=:userchatId";


        //call userchat service and load userchat
        //url = {userchatadress}/userchat/{id}
        UserChatDto userchat = userchatClient.getUserchatById(userchatId);
        System.out.println("userchat = " + userchat);
        List<MessageDto> messageDtos = new ArrayList<>();

        return jdbcTemplate.query(
                query,
                Map.of("userchatId", userchatId),
                (resultSet, index) -> {
                    return new Message(
                            resultSet.getLong("id"),
                            resultSet.getString("text"),
                            resultSet.getLong("userchat_id"),
                            resultSet.getDate("creation_date")

                    );
                }
        );
    }
}
