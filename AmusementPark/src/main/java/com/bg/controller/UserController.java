package com.bg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bg.dto.UserDetailsDto;
import com.bg.entity.Users;
import com.bg.repository.UserRepository;
import com.bg.service.UserService;
import com.bg.util.AllConstants;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/add")
	public ResponseEntity<?> registration(@RequestBody UserDetailsDto userDto) {
		if (userRepository.existsByEmail(userDto.getEmail())) {
			return new ResponseEntity<>(AllConstants.E_MESSAGE, HttpStatus.BAD_REQUEST);
		}

		Users user = new Users();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getFirstname());
		user.setAge(userDto.getAge());

		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		userRepository.save(user);

		return new ResponseEntity<>(AllConstants.USER_SUCCESS_MESSAGE, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody UserDetailsDto userDto) {
		Authentication authentication = authenticationProvider
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		userRepository.findByEmail(userDto.getEmail());
		if (userDto.getEmail().equalsIgnoreCase(userDto.getEmail())) {
			return new ResponseEntity<>(AllConstants.USER_SUCCESS_MESSAGE2, HttpStatus.OK);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("Check your email and password..", HttpStatus.BAD_REQUEST);
	}

	// get all rest api
	@GetMapping("/getall")
	public ResponseEntity<?> fetchAllUsers() {
		try {
			List<UserDetailsDto> allUsers = userService.getAllUsers();
			if (allUsers == null || allUsers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

// return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);

// add user rest api
//		@PostMapping("/add")
//		public ResponseEntity<String> addUser(@RequestBody UserDetailsDto userDto) {
//			String string = null;
//			try {
//				if (userDto == null) {
//					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//				} else {
//
//					string = userService.addUser(userDto);
//					logger.info(AllConstants.LOG_MESSAGE);
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			return new ResponseEntity<>(string, HttpStatus.CREATED);
//		}

//		// update rest api
//		@PutMapping("/update")
//		public ResponseEntity<?> updateUser(@RequestBody UserDetailsDto userDto) {
//			try {
//				String string = userService.addUser(userDto);
//				return new ResponseEntity<>(string, HttpStatus.OK);
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//				return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
//			}
//		}

// login rest api
//	@PostMapping("/login")
//	public ResponseEntity<String> validateUser(@RequestBody LoginReq loginReq) {
//		userService.validateUser(loginReq.getEmail(), loginReq.getPassword());
//		return new ResponseEntity<>("LOGIN SUCCESSFULLY!", HttpStatus.OK);
//	}

//	@GetMapping("/u/{userName}")
//	public ResponseEntity<List<Users>> findorderByUserName(@PathVariable String userName) {
//		return new ResponseEntity<List<Users>>(userService.getBookinhByUserName(userName), HttpStatus.OK);
//	}
// user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

// add user rest api
//		@PostMapping("/add")
//		public ResponseEntity<String> addUser(@RequestBody UserDetailsDto userDto) {
//			String string = null;
//			try {
//				if (userDto == null) {
//					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//				} else {
//
//					string = userService.addUser(userDto);
//					logger.info(AllConstants.LOG_MESSAGE);
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			return new ResponseEntity<>(string, HttpStatus.CREATED);
//		}
