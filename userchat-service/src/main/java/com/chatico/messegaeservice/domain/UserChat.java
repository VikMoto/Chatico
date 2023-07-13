package com.chatico.messegaeservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;


import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "user_chat")
@Data
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id", "name" })
public class UserChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    private String userPic;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;


    private String locale;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

    @OneToMany(
            mappedBy = "userChat",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
//    @JoinColumn(name = "group_chat_id")
    private Set<UserContacts> contacts = new TreeSet<>();

    public enum Gender{
        MALE, FEMALE;

    }

    public enum Role{
        AUTHOR, READER;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChat userChat = (UserChat) o;
        return this.id != null && Objects.equals(id, userChat.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
