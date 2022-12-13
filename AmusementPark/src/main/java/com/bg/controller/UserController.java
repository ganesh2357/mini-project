package com.bg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bg.entity.UserDetails;
import com.bg.payload.LoginReq;
import com.bg.service.UserService;
import com.bg.util.AllConstants;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// add user rest api
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody UserDetails user) {
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			userService.addUser(user);
			logger.info("Adding.......");
			return new ResponseEntity<>(AllConstants.SUCCESS_MESSAGE, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// update rest api
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDetails user) {
		try {
			userService.addUser(user);
			logger.info("USER DETAILS ARE UPDATED...");
			return new ResponseEntity<>(AllConstants.SUCCESS_MESSAGE, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// get all rest api
	@GetMapping("/getall")
	public ResponseEntity<?> fetchAllUsers() {
		try {
			List<UserDetails> allUsers = userService.getAllUsers();
			logger.info("FETCHING ALL USERS..");
			return new ResponseEntity<>(allUsers, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// login rest api
	@PostMapping("/login")
	public ResponseEntity<UserDetails> validateUser(@RequestBody LoginReq loginReq) {
		UserDetails user = userService.validateUser(loginReq.getUserName(), loginReq.getPassword());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
