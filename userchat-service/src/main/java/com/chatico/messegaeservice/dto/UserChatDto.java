package com.chatico.messegaeservice.dto;

import com.chatico.messegaeservice.domain.UserChat;

import java.time.LocalDateTime;

public record UserChatDto(String name, String userPic, UserChat.Role role, LocalDateTime lastVisit) {

}
