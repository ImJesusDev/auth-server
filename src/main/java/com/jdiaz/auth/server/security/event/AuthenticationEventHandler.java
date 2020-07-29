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

import brave.Tracer;

@Component
public class AuthenticationEventHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationEventHandler.class);

	@Value("${config.security.oauth.client.id}")
	private String clientId;

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private Tracer tracer;

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
			String message = "Error updating last connection for user" + authentication.getName();
			log.error(message);
			tracer.currentSpan().tag("user.error.update-last-connection", message + ": " + e.getMessage());
		}
		String message = "Succesful login of user: " + user.getUsername();
		tracer.currentSpan().tag("user.success.login", message);
		log.info(message);

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error on login: " + exception.getMessage();
		tracer.currentSpan().tag("user.success.login", message);
		log.info(message);

	}

}
