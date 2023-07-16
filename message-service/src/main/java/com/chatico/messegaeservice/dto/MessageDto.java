package com.chatico.messegaeservice.dto;

import com.chatico.messegaeservice.domain.Message;

import java.time.LocalDateTime;
import java.util.List;

public record MessageDto(Long id, String text, List<Message> messages) {
}
