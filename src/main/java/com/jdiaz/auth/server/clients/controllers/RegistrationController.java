package com.jdiaz.auth.server.clients.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jdiaz.auth.server.services.UserServiceInterface;
import com.jdiaz.users.commons.models.entity.User;

@Controller
public class RegistrationController {

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/register-user")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userService.registerUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}


}
