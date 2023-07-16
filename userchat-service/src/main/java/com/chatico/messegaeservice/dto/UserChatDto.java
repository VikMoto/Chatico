package com.chatico.messegaeservice.dto;


import java.util.List;

public record UserChatDto(String name, List<MessageDto> messageDtos) {

}
