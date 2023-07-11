package com.chatico.messegaeservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class UserContacts {

    @Id
    private Long id;

    @MapsId("userChat_id")
    @ManyToOne
    private UserChat contact;
}
