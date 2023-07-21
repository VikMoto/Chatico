package com.chatico.messegaeservice.dto;

import com.chatico.messegaeservice.domain.GroupChat;
import com.chatico.messegaeservice.domain.Message;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MessageDto(String text, LocalDateTime creationDate, UserChatDto userChatDto, String groupChatName) {
}
