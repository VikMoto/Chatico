package com.chatico.userservice.dto;

import com.chatico.userservice.domain.Role;
import com.chatico.userservice.domain.UserChat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class UserchatRegDto {

    private String username;

    private String userPic;

    private String email;

    private UserChat.Gender gender;

    private Set<Role> roles;

    private String locale;

    private LocalDate birthday;
}

