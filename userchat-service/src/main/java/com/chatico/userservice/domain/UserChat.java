package com.chatico.userservice.domain;

import com.chatico.userservice.security.AuthenticationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "user_chat")
@Data
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id", "name" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChat {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String locale;
    private LocalDate birthday;
    private String userPic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type")
    private AuthenticationType authType;

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
