package com.chatico.messegaeservice.domain;

import com.chatico.messegaeservice.dto.MessageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
//@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @NaturalId
    private Long userchatId;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;


    @ManyToOne
    private GroupChat groupChat;

    @ManyToOne
    private PrivatChat privatChat;

    public Message(long id, String text, long userchatId, Date creationDate) {
        this.id = id;
        this.text = text;
        this.userchatId = userchatId;
        this.creationDate = new Timestamp(creationDate.getTime()).toLocalDateTime();
    }
}
