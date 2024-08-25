package com.spring.delivery.service.http.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.delivery.domain.request.RequestAuthorizationCode;
import com.spring.delivery.domain.response.ResponseGoogleAuthorizationCode;

@FeignClient(name = "google-service", url = "https://oauth2.googleapis.com")
public interface HttpGoogleOauth2 {
	@PostMapping(value = "/token")
	ResponseGoogleAuthorizationCode getAccessToken(@RequestBody RequestAuthorizationCode requestAuthorizationCode);
}
