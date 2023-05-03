package com.bg.service;

import java.util.List;

import com.bg.dto.UserDetailsDto;

public interface UserService {

//	public String addUser(UserDetailsDto userDto);

	public String updateUser(UserDetailsDto userDto);

	public List<UserDetailsDto> getAllUsers();

	// public String validateUser(String userName, String password);

	// public List<Users> getBookinhByUserName(String userName);

}
