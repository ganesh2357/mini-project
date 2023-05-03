package com.bg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bg.dto.UserDetailsDto;
import com.bg.entity.Users;
import com.bg.exception.BusinessException;
import com.bg.repository.UserRepository;
import com.bg.util.AllConstants;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	ModelMapper modelmap;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String updateUser(UserDetailsDto userDto) {
		try {

			Users user = convertToEntity(userDto);
			userRepo.save(user);
			return AllConstants.USER_SUCCESS_MESSAGE1;

		} catch (IllegalArgumentException e) {
			throw new BusinessException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public List<UserDetailsDto> getAllUsers() {
		List<Users> users = null;
		try {
			users = userRepo.findAll();

		} catch (Exception e) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		List<UserDetailsDto> user = users.stream().map(this::convertToDto).collect(Collectors.toList());

		return user;

	}

	public Users convertToEntity(UserDetailsDto userDto) {
		Users user = modelmap.map(userDto, Users.class);
		return user;
	}

	public UserDetailsDto convertToDto(Users user) {
		UserDetailsDto userDto = modelmap.map(user, UserDetailsDto.class);
		return userDto;
	}

//	@Override
//	public String addUser(UserDetailsDto userDto) {
//		try {
//			if (userRepo.existsByEmail(userDto.getEmail())) {
//				return AllConstants.E_MESSAGE;
//			}
//			Users user = convertToEntity(userDto);
//			String password = user.getPassword();
//			String encodePassword = passwordEncoder.encode(password);
//			user.setPassword(encodePassword);
//			user = userRepo.save(user);
//			if (user.getUserId() == null) {
//				return AllConstants.ERROR_MESSAGE3;
//			} else {
//				return AllConstants.USER_SUCCESS_MESSAGE;
//			}
//		} catch (Exception e) {
//			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

//	@Override
//	public String validateUser(String username, String password) {
//		Authentication authentication = authenticationProvider
//				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		Optional<Users> user = userRepo.findByEmail(username);
//
//		if (username.equalsIgnoreCase(username)) {
//			return "USER LOGIN SUCCESSFULLY..";
//		}
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return "INVALID EMAIL&PASSWORD";
//	}

//	@Override
//	public List<Users> getBookinhByUserName(String userName) {
//		List<Users> user = null;
//		try {
//			if (userName == null || !userRepo.existsByuserName(userName)) {
//				throw new UserNotFoundException(AllConstants.ERROR_MESSAGE);
//			} else {
//				user = userRepo.findByuserName(userName);
//			}
//		} catch (Exception e) {
//			logger.error(userName, e.getStackTrace(), e);
//			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return user;
//	}

//	@Override
//	public String addUser(UserDetailsDto userDto) {
//		try {
//			Users user = convertToEntity(userDto);
//			String password = user.getPassword();
//			String encodePassword = passwordEncoder.encode(password);
//			user.setPassword(encodePassword);
//			user = userRepo.save(user);
//			if (user.getUserId() == null) {
//				return AllConstants.ERROR_MESSAGE3;
//			} else {
//				return AllConstants.USER_SUCCESS_MESSAGE;
//			}
//		} catch (Exception e) {
//			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

//	@Override
//	public Users validateUser(String username, String password) {
//		if (!userRepo.existsByusername(username) || !userRepo.existsByPassword(password)) {
//			throw new UserNotFoundException(AllConstants.ERROR_MESSAGE);
//		}
//		return userRepo.validateUserCredentials(username, password);
//	}

}
