package com.chatico.messegaeservice.repositiry;

import com.chatico.messegaeservice.domain.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepository extends JpaRepository<UserChat, Long> {
}
