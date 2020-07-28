package com.jdiaz.auth.server.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.commons.jdiaz.users.models.entity.User;
import com.jdiaz.auth.server.services.UserServiceInterface;

@Component
public class AuthenticationEventHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationEventHandler.class);

	@Value("${config.security.oauth.client.id}")
	private String clientId;

	@Autowired
	private UserServiceInterface userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		if (authentication.getName().equalsIgnoreCase(clientId)) {
			return;
		}

		try {
			User authenticatedUser = userService.findByUsername(authentication.getName());
			userService.updateUserLastConnection(authenticatedUser.getId());
		} catch (Exception e) {
			log.info("Error updating user");
		}
		String message = "Succesful login of user: " + user.getUsername();
		System.out.println(message);
		log.info(message);

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error on login: " + exception.getMessage();
		System.out.println(message);
		log.info(message);

	}

}
