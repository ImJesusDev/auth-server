package com.jdiaz.auth.server.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.commons.jdiaz.users.models.entity.User;
import com.jdiaz.auth.server.clients.UserFeignClient;


@Service
public class UserService implements UserServiceInterface, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			User user = userClient.searchUsername(username);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					user.getEnabled(), true, true, true, authorities);

		} catch (Exception e) {
			log.error("User not found");
			throw new UsernameNotFoundException("User not found");
		}
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return userClient.searchUsername(username);

	}

	@Override
	public User updateUser(User user, Long id) throws Exception {
		return userClient.updateUser(user, id);

	}

	@Override
	public User registerUser(User user) throws Exception {
		return userClient.registerUser(user);

	}

}
