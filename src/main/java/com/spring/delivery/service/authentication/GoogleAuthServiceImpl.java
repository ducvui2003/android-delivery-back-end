package com.spring.delivery.service.authentication;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.spring.delivery.domain.request.RequestAuthorizationCode;
import com.spring.delivery.domain.response.ResponseGoogleAuthorizationCode;
import com.spring.delivery.domain.response.UserInfoGoogle;
import com.spring.delivery.service.http.google.HttpGoogleApi;
import com.spring.delivery.service.http.google.HttpGoogleOauth2;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GoogleAuthServiceImpl implements GoogleAuthService {
	GoogleClientSecrets googleClientSecrets;
	HttpGoogleOauth2 httpGoogleService;
	HttpGoogleApi httpGoogleApi;

	private ResponseGoogleAuthorizationCode getResponseGoogleAuthorizationCode(String authCode) {
		String AUTHORIZATION_CODE = "authorization_code";
		RequestAuthorizationCode requestAuthorizationCode = RequestAuthorizationCode.builder()
				.code(authCode)
				.clientId(googleClientSecrets.getDetails().getClientId())
				.clientSecret(googleClientSecrets.getDetails().getClientSecret())
				.redirectUri(null)
				.grantType(AUTHORIZATION_CODE)
				.build();

		try {
			return httpGoogleService.getAccessToken(requestAuthorizationCode);
		} catch (RuntimeException e) {
			throw new AppException(AppErrorCode.GOOGLE_AUTHENTICATION_FAILED);
		}
	}

	public UserInfoGoogle getProfileByAuthCode(String authCode) {
		try {
			ResponseGoogleAuthorizationCode responseGoogleAuthorizationCode =
					getResponseGoogleAuthorizationCode(authCode);
			return httpGoogleApi.getUserInfo(
					responseGoogleAuthorizationCode.tokenType() + " " + responseGoogleAuthorizationCode.accessToken());
		} catch (RuntimeException e) {
			throw new AppException(AppErrorCode.GOOGLE_AUTHENTICATION_FAILED);
		}
	}

	public UserInfoGoogle getProfileByAccessToken(String accessToken) {
		try {
			return httpGoogleApi.getUserInfo("Bearer " + accessToken);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new AppException(AppErrorCode.GOOGLE_AUTHENTICATION_FAILED);
		}
	}
}
