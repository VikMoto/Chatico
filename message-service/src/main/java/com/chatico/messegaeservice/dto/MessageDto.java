package com.chatico.messegaeservice.dto;

import com.chatico.messegaeservice.domain.Message;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MessageDto(Long id, String text, LocalDateTime creationDate) {
}
