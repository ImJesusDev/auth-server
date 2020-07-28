package com.jdiaz.auth.server.configurations;

import java.io.IOException;


import com.jdiaz.auth.server.exceptions.MyBadRequestException;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            try {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                String responseBody = new String(bodyData);
                return new MyBadRequestException(responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}