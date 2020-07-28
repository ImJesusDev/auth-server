package com.jdiaz.auth.server.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.commons.jdiaz.users.models.entity.User;

public interface UserServiceInterface {
	
	public User findByUsername(String username) throws Exception;
	
	public User updateUserLastConnection(@PathVariable Long id) throws Exception;
	
	public User registerUser(@RequestBody User user) throws Exception;

}
