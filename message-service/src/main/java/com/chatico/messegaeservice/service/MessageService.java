package com.chatico.messegaeservice.service;

import com.chatico.messegaeservice.client.UserchatClient;
import com.chatico.messegaeservice.domain.GroupChat;
import com.chatico.messegaeservice.domain.Message;
import com.chatico.messegaeservice.dto.MessageDto;
import com.chatico.messegaeservice.dto.UserChatDto;
import com.chatico.messegaeservice.repositiry.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final MessageRepository messageRepository;
    private final UserchatClient userchatClient;
//    private final UserchatServiceClient serviceClient;

    public List<MessageDto> getMessagesByGroupChatId(Long groupChatId, Long userchatId) {
        String query = "SELECT m.id, m.text, m.userchat_id, m.creation_date, m.group_chat_id, gc.name FROM message m JOIN group_chat gc on m.group_chat_id = gc.id WHERE group_chat_id=:groupChatId";
        List<MessageDto> messageDtos = new ArrayList<>();
        GroupChat groupChat = new GroupChat();
        List<Message> messages = jdbcTemplate.query(
                query,
                Map.of("groupChatId", groupChatId),
                (resultSet, index) -> {
                    long messageId = resultSet.getLong("id");
                    String text = resultSet.getString("text");
                    long userChatId = resultSet.getLong("userchat_id");
                    Date creationDate = resultSet.getDate("creation_date");
                    long groupChatIdResult = resultSet.getLong("group_chat_id");
                    String groupName = resultSet.getString("name");

                    // Fetch the GroupChat object using the groupChatId

                    groupChat.setId(groupChatIdResult);
                    groupChat.setName(groupName);

                    return new Message(messageId, text, userChatId, creationDate, groupChat);

//                Map.of("groupChatId", groupChatId),
//                (resultSet, index) -> {
//                    return new Message(
//                            resultSet.getLong("id"),
//                            resultSet.getString("text"),
//                            resultSet.getLong("userchat_id"),
//                            resultSet.getDate("creation_date"),
//                            resultSet.getLong("group_chat_id"))
//                            );
                }
        );
        if (!messages.isEmpty()) {
            UserChatDto userChatDto = userchatClient.getById(messages.get(0).getUserchatId());
            return messages.stream()
                    .map(it -> convertToDto(it,userChatDto, groupChat.getName()))
                    .toList();
        } else {
            // Handle the case when the messages list is empty, maybe return an empty list or throw an exception.
            // Example:
            return Collections.emptyList();
        }
    }

    public List<MessageDto> getMessagesByUserchatId(Long userchatId) {
        String query = "SELECT m.id, m.text, m.userchat_id, m.creation_date FROM message m WHERE userchat_id=:userchatId";
        List<MessageDto> messageDtos = new ArrayList<>();
        List<Message> messages = jdbcTemplate.query(
                query,
                Map.of("userchatId", userchatId),
                (resultSet, index) -> {
                    return new Message(
                            resultSet.getLong("id"),
                            resultSet.getString("text"),
                            resultSet.getLong("userchat_id"),
                            resultSet.getDate("creation_date"));
                }
        );
        UserChatDto userChatDto = userchatClient.getById(messages.get(0).getUserchatId());
        return messages.stream()
                .map(it -> convertToDto(it,userChatDto,null))
                .toList();

    }

    public MessageDto convertToDto(Message message, UserChatDto userChatDto, String  groupChatName) {

        return MessageDto.builder()
                .text(message.getText())
                .creationDate(message.getCreationDate())
                .userChatDto(userChatDto)
                .groupChatName(groupChatName)
                .build();
    }
}
