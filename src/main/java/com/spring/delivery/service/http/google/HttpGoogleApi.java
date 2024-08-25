/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 6:32 AM - 13/08/2024
 * User: lam-nguyen
 **/
package com.spring.delivery.service.http.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.spring.delivery.domain.response.UserInfoGoogle;

@FeignClient(name = "login-google-service", url = "https://www.googleapis.com/oauth2/v3")
public interface HttpGoogleApi {
	@PostMapping(value = "/userinfo")
	UserInfoGoogle getUserInfo(@RequestHeader("Authorization") String accessToken);
}
