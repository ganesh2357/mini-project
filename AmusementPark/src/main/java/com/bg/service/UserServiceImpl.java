package com.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bg.entity.UserDetails;
import com.bg.exception.BusinessException;
import com.bg.exception.UserNotFoundException;
import com.bg.repository.UserRepository;
import com.bg.util.AllConstants;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(UserDetails user) {
		try {
			userRepo.save(user);
			return AllConstants.USER_SUCCESS_MESSAGE;
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String updateUser(UserDetails user) {
		try {
			userRepo.save(user);
			return AllConstants.USER_SUCCESS_MESSAGE1;
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
			throw new UserNotFoundException(AllConstants.ERROR_MESSAGE);
		}
		return userRepo.validateUserCredentials(userName, password);
	}

	@Override
	public List<UserDetails> getBookinhByUserName(String userName) {

		return userRepo.findByuserName(userName);
	}

}
