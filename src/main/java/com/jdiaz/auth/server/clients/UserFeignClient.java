package com.jdiaz.auth.server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdiaz.auth.server.configurations.FeignConfig;
import com.jdiaz.users.commons.models.entity.User;

@FeignClient(name="users-service", configuration = FeignConfig.class)
public interface UserFeignClient {
	
	@GetMapping("/users/search-username")
	public User searchUsername(@RequestParam String username) throws Exception;
	
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception;
	
	@PostMapping("/register-user")
	public User registerUser(@RequestBody User user) throws Exception;

}
