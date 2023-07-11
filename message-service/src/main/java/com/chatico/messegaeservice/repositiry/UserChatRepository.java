package com.chatico.messegaeservice.repositiry;

import com.chatico.messegaeservice.domain.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
}
