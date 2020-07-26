package com.jdiaz.auth.server.services;

import com.jdiaz.users.commons.models.entity.User;

public interface UserServiceInterface {
	
	public User findByUsername(String username);

}
