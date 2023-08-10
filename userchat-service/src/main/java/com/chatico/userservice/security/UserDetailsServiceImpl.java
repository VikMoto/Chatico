package com.chatico.userservice.security;

import com.chatico.userservice.domain.Role;
import com.chatico.userservice.domain.UserChat;
import com.chatico.userservice.repositiry.UserChatRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {


	private final UserChatRepository userChatRepository;

	public UserDetailsServiceImpl(UserChatRepository userChatRepository) {
		this.userChatRepository = userChatRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserChat userChat = userChatRepository.getUserByUsername(username);
		
		if (userChat == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(userChat);
	}

    public void processOAuthPostLogin(String username) {
		UserChat existUserChat = userChatRepository.getUserByUsername(username);

		if (existUserChat == null) {
			UserChat newUserChat = new UserChat();
			newUserChat.setUsername(username);
			newUserChat.setEmail(username);
			newUserChat.setAuthType(AuthenticationType.GOOGLE);
			newUserChat.setRoles(Set.of(Role.USER));
			newUserChat.setEnabled(true);

			userChatRepository.save(newUserChat);

			System.out.println("Created new userChat: " + username);
		}
    }
}
