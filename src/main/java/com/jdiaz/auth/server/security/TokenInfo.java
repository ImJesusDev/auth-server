package com.jdiaz.auth.server.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.jdiaz.auth.server.services.UserServiceInterface;
import com.jdiaz.users.commons.models.entity.User;

@Component
public class TokenInfo implements TokenEnhancer {

	@Autowired
	public UserServiceInterface userInterface;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		User user;
		try {
			user = userInterface.findByUsername(authentication.getName());
			info.put("first_name", user.getFirstName());
			info.put("last_name", user.getLastName());
			info.put("email", user.getEmail());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accessToken;
	}

}
