package com.chatico.messegaeservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
@Data
@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
public class GroupChat implements Chat{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long userchatId;

    @OneToMany
    @JoinColumn(name = "group_chat_id")
    private List<Message> messages;

    @Override
    public void sendMessage(Long id, Message message) {

    }

    @Override
    public void getMessage(Long id, Message message) {

    }


}
