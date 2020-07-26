package com.jdiaz.auth.server.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jdiaz.users.commons.models.entity.User;

public interface UserServiceInterface {
	
	public User findByUsername(String username);
	
	public User updateUser(@RequestBody User user, @PathVariable Long id);

}
