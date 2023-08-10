package com.chatico.userservice.security;




import com.chatico.userservice.domain.Role;
import com.chatico.userservice.domain.UserChat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {

	private UserChat userChat;
	
	public MyUserDetails(UserChat userChat) {
		this.userChat = userChat;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = userChat.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return userChat.getPassword();
	}

	@Override
	public String getUsername() {
		return userChat.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userChat.isEnabled();
	}

}
