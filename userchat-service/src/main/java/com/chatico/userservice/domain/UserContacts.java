package com.chatico.userservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "user_chat_id")
    private UserChat userChat;

}
