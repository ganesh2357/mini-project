package com.bg.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bg.entity.Users;
import com.bg.exception.UserNotFoundException;
import com.bg.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository urepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
		Optional<Users> users = urepo.findByEmail(email);
		return users.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND" + email));

	}

}
