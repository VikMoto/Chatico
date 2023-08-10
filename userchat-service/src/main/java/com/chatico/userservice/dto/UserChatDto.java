package com.chatico.userservice.dto;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
@Builder
public record UserChatDto(Long id, String username, String userPic, LocalDateTime lastVisit) {

}

