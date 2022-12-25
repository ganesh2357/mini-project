package com.bg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// add user rest api
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody UserDetails user) {
		String str = null;
		try {
			if (user == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				str = userService.addUser(user);
				logger.info(AllConstants.LOG_MESSAGE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(str, HttpStatus.CREATED);
	}

	// update rest api
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDetails user) {
		try {
			String str = userService.addUser(user);
			return new ResponseEntity<>(str, HttpStatus.OK);
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
			if (allUsers == null || allUsers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// login rest api
	@PostMapping("/login")
	public ResponseEntity<UserDetails> validateUser(@RequestBody LoginReq loginReq) {
		UserDetails user = userService.validateUser(loginReq.getUserName(), loginReq.getPassword());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/u/{userName}")
	public ResponseEntity<?> findorderByUserName(@PathVariable String userName) {
		List<UserDetails> user = null;
		try {
			if (userName == null || userName.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				user = userService.getBookinhByUserName(userName);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
