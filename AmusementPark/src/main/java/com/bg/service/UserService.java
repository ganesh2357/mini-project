package com.bg.service;

import java.util.List;

import com.bg.entity.UserDetails;

public interface UserService {

	public String addUser(UserDetails user);

	public String updateUser(UserDetails user);

	public List<UserDetails> getAllUsers();

	public UserDetails validateUser(String userName, String password);

}
