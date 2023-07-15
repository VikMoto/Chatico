package com.chatico.messegaeservice.dto;

import com.chatico.messegaeservice.domain.UserChat;

import java.time.LocalDateTime;
import java.util.List;

public record UserChatDto(String name, String userPic, List<MessageDto> messageDtos) {

}
