package com.chatico.messegaeservice.repositiry;

import com.chatico.messegaeservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findAllByUserchatId(Long userchatId);
}
