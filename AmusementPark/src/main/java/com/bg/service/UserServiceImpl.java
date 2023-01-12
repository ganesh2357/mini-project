package com.bg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bg.dto.UserDetailsDto;
import com.bg.entity.UserDetails;
import com.bg.exception.BusinessException;
import com.bg.exception.UserNotFoundException;
import com.bg.repository.UserRepository;
import com.bg.util.AllConstants;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelamp;
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
	public List<UserDetailsDto> getAllUsers() {
		List<UserDetails> users = null;
		try {
			users = userRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return users.stream().map(user -> 
				modelamp.map(user, UserDetailsDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDetails validateUser(String userName, String password) {
		if (!userRepo.existsByuserName(userName) || !userRepo.existsByPassword(password)) {
			throw new UserNotFoundException(AllConstants.ERROR_MESSAGE);
		}
		return userRepo.validateUserCredentials(userName, password);
	}

	@Override
	public List<UserDetails> getBookinhByUserName(String userName) {
		List<UserDetails> user = null;
		try {
			if (userName == null || !userRepo.existsByuserName(userName)) {
				throw new UserNotFoundException(AllConstants.ERROR_MESSAGE);
			} else {
				user = userRepo.findByuserName(userName);
			}
		} catch (Exception e) {
			logger.error(userName, e.getStackTrace(), e);
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return user;
	}

}
