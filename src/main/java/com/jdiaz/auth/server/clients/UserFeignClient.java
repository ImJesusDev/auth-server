package com.jdiaz.auth.server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdiaz.users.commons.models.entity.User;

@FeignClient(name="users-service")
public interface UserFeignClient {
	
	@GetMapping("/users/search-username")
	public User searchUsername(@RequestParam String username);

}
