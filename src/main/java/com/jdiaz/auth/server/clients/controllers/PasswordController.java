package com.jdiaz.auth.server.clients.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/encrypt-password")
	public ResponseEntity<?> encruyptPassword(@RequestParam String password) {
		return ResponseEntity.ok(passwordEncoder.encode(password));
	}

}
