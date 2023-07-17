package com.chatico.messegaeservice.dto;

import com.chatico.messegaeservice.domain.UserChat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserchatRegDto {

    private String name;


    private String userPic;


    private String email;


    private UserChat.Gender gender;


    private UserChat.Role role;

    private String locale;

    private LocalDate birthday;
}
