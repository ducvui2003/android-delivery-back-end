package com.spring.delivery.service.authentication;

import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthenticationService {
    User register(String idToken, User user);

    ResponseAuthentication getAccessToken(String email);

    void logout(String email, String accessToken, String refreshToken);

    User getUserByEmail(String email);

    boolean isVerify(String email);

    boolean checkBeforeRegister(String email, String phoneNumber);

    User getUserByPhoneNumber(String phoneNumber);

    ResponseAuthentication loginByPhoneNumber();

    ResponseAuthentication loginByEmail();

    void createUserOAuth2(OAuth2User oAuth2User);
}
