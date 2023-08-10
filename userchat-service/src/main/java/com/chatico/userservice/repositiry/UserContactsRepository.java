package com.chatico.userservice.repositiry;

import com.chatico.userservice.domain.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepository extends JpaRepository<UserChat, Long> {
}
