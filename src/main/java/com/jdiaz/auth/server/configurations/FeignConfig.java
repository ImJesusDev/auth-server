package com.jdiaz.auth.server.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	// other beans here
	@Bean
	public ErrorDecoder feignErrorDecoder() {
		return new FeignErrorDecoder();
	}
}