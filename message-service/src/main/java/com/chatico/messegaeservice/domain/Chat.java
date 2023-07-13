package com.chatico.messegaeservice.domain;

public interface Chat {
    void sendMessage(Long id, Message message );
    void getMessage(Long id, Message message);
}
