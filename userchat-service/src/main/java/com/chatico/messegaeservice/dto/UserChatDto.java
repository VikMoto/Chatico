package com.chatico.messegaeservice.dto;


import lombok.Builder;

import java.util.List;
@Builder
public record UserChatDto(Long id, String name, List<MessageDto> messageDtos) {

}
