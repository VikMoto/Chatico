package com.chatico.messegaeservice.dto;

import java.time.LocalDateTime;

public record MessageDto(Long id, String text, LocalDateTime creationDate) {
}
