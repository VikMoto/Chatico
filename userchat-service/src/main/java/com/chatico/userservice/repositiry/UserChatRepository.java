package com.chatico.userservice.repositiry;

import com.chatico.userservice.domain.UserChat;
import com.chatico.userservice.security.AuthenticationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserChatRepository extends CrudRepository<UserChat, Long> {

    @Query("SELECT u FROM UserChat u WHERE u.username = :username")
    public UserChat getUserByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE UserChat u SET u.authType = ?2 WHERE u.username = ?1")
    public void updateAuthenticationType(String username, AuthenticationType authType);
}
