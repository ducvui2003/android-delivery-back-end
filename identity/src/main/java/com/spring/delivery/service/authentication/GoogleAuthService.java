/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 4:49 PM - 11/08/2024
 * User: lam-nguyen
 **/
package com.spring.delivery.service.authentication;

import com.spring.delivery.domain.response.UserInfoGoogle;

public interface GoogleAuthService {
	UserInfoGoogle getProfileByAuthCode(String authCode);

	UserInfoGoogle getProfileByAccessToken(String accessToken);
}
