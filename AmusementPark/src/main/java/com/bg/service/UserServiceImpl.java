package com.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bg.entity.UserDetails;
import com.bg.exception.BusinessException;
import com.bg.exception.UserNotFoundException;
import com.bg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(UserDetails user) {

		if (user.getFirstName().isEmpty()) {
			throw new BusinessException(HttpStatus.NOT_FOUND);
		}
		try {
			userRepo.save(user);
			return "USER DETAILS ARE SAVED SUCCESSFULLY...";
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public String updateUser(UserDetails user) {
		try {
			userRepo.save(user);
			return "USER DETAILS ARE UPDATED...";
		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public List<UserDetails> getAllUsers() {
		List<UserDetails> users = null;
		try {
			users = userRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return users;
	}

	@Override
	public UserDetails validateUser(String userName, String password) {
		if (!userRepo.existsByuserName(userName)) {
			throw new UserNotFoundException("User Not Found..");
		}
		return userRepo.validateUserCredentials(userName, password);
	}

}
