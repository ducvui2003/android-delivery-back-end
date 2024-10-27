package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestLoginGoogleMobileByAuthCode;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.domain.response.UserInfoGoogle;
import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.service.authentication.GoogleAuthService;
import com.spring.delivery.util.CustomOAuth2User;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OAuth2GoogleController {
    GoogleAuthService googleAuthService;
    AuthenticationService authenticationService;
    SecurityUtil securityUtil;

    @ApiMessage("Login google")
    @PostMapping("/login-google-mobile")
    public ResponseEntity<ResponseAuthentication> loginGoogle(@RequestBody RequestLoginGoogleMobileByAuthCode request) {
        UserInfoGoogle profileUserGoogle = googleAuthService.getProfileByAuthCode(request.authCode());
        log.info("profileUserGoogle: {}", profileUserGoogle);

//        Append to SecurityContextHolder
        OAuth2User authenticationToken = new CustomOAuth2User(profileUserGoogle);
        authenticationService.createUserOAuth2(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authenticationToken, null, Collections.emptyList()));
        ResponseAuthentication response = authenticationService.loginByEmail();
        ResponseCookie cookie = securityUtil.updateRefreshToken(response.getRefreshToken());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
    }
}
