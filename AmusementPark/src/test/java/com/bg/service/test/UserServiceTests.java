package com.bg.service.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bg.entity.Booking;
import com.bg.entity.UserDetails;
import com.bg.repository.UserRepository;
import com.bg.service.UserService;

class UserServiceTests {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepo;

	@Test
	void addUserTest() {
		UserDetails user = new UserDetails(1, "Ramesh", 
				"Reddi", 25, "ramesh", "Ramesh123", new ArrayList<Booking>());
		when(userRepo.save(user)).thenReturn(user);

	}

}
