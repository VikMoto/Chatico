package com.chatico.messegaeservice.repositiry;

import com.chatico.messegaeservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{
}
