package com.spring.delivery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.spring.delivery.config.properties.CookieProperties;
import com.spring.delivery.domain.request.*;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.domain.response.UserInfoGoogle;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.User;
import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.service.authentication.GoogleAuthService;
import com.spring.delivery.service.authentication.VerifyService;
import com.spring.delivery.service.token.TokenService;
import com.spring.delivery.util.MyPhoneNumberUtil;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.anotation.ApiMessage;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
	UserMapper userMapper = UserMapper.INSTANCE;
	CookieProperties cookieProperties;
	AuthenticationService authenticationService;
	VerifyService verifyService;
	AuthenticationManagerBuilder authenticationManagerBuilder;
	SecurityUtil securityUtil;
	TokenService tokenService;
	GoogleAuthService googleAuthService;

	@ApiMessage("Login")
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<ResponseAuthentication> login(@Valid @RequestBody RequestLogin userLogin) {
		if(!MyPhoneNumberUtil.isPhoneNumberValid(userLogin.region(),userLogin.phoneNumber()))
			throw new AppException(AppErrorCode.PHONE_NUMBER_INVALID);

		// Nạp input gồm username và password vào Security
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(MyPhoneNumberUtil.formatPhoneNumber(userLogin.region(),userLogin.phoneNumber()), userLogin.password());
		// Sử dụng method loadUserDetail đã implement để lấy ra user trong db
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		// Get user đã đăng nhập thành công vào SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return login(MyPhoneNumberUtil.formatPhoneNumber(userLogin.region(),userLogin.phoneNumber()));
	}

	@ApiMessage("check info before Register")
	@PostMapping("/check-before-register")
	public ResponseEntity<Void> checkBeforeRegister(@Valid @RequestBody RequestCheckBeforeRegister requestCheckBeforeRegister) {
		if(!MyPhoneNumberUtil.isPhoneNumberValid(requestCheckBeforeRegister.region(),requestCheckBeforeRegister.phoneNumber()))
			throw new AppException(AppErrorCode.PHONE_NUMBER_INVALID);
		authenticationService.checkBeforeRegister(MyPhoneNumberUtil.formatPhoneNumber(requestCheckBeforeRegister.region(),requestCheckBeforeRegister.phoneNumber()), requestCheckBeforeRegister.phoneNumber());
		return ResponseEntity.ok().build();
	}

	@ApiMessage("Register")
	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody RequestRegister userRegister) {
		if(!MyPhoneNumberUtil.isPhoneNumberValid(userRegister.getRegion(),userRegister.getPhoneNumber()))
			throw new AppException(AppErrorCode.PHONE_NUMBER_INVALID);
		userRegister.setPhoneNumber(MyPhoneNumberUtil.formatPhoneNumber(userRegister.getRegion(),userRegister.getPhoneNumber()));
		User user = authenticationService.register(userRegister.getIdToken(), userMapper.toUser(userRegister));
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	// Sử dụng để client lấy lại data người dùng khi F5 trang
	// (người dùng đã login trước đó và cookie đã set refresh)
	@ApiMessage("Get info account")
	@GetMapping("/account")
	public ResponseEntity<ResponseAuthentication.UserGetAccount> getAccount() {
		String email = SecurityUtil.getCurrentUserLogin().orElse("");
		User user = authenticationService.getUserByEmail(email);
		if (user == null)
			throw new AppException("Xử lý if else user trong hàm getAccount của AuthenticationController");

		ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);
		return ResponseEntity.ok().body(new ResponseAuthentication.UserGetAccount(userDTO));
	}

	// Sử dụng để lấy lại access token khi hết hạn
	@ApiMessage("Refresh token")
	@PostMapping("/refresh-token")
	public ResponseEntity<ResponseAuthentication> refreshAccessToken(
			@CookieValue(name = "refresh_token") String refreshToken) {
		securityUtil.validateRefreshToken(refreshToken);
		//  Check user by refresh authCode
		String email =
				SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
		User user = authenticationService.getUserByEmail(email);

		ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);

		String accessToken = securityUtil.createAccessToken(userDTO);

		tokenService.saveToken(user.getEmail(), accessToken);

		ResponseAuthentication responseBody = ResponseAuthentication.builder()
				.user(userDTO)
				.accessToken(accessToken)
				.build();

		return ResponseEntity.ok().body(responseBody);
	}

	@ApiMessage("Logout")
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(
			@CookieValue(
							name = "${app.cookie.key.refreshToken}",
							defaultValue = "${app.cookie.defaultValue.refreshToken}")
					String refreshToken,
			HttpServletRequest request)
			throws AppException {
		if (refreshToken.equals(cookieProperties.getRefreshTokenDefault()))
			throw new AppException(AppErrorCode.REFRESH_TOKEN_NOT_FOUND);
		String email =
				SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
		String accessToken = SecurityUtil.getAccessTokenFromRequest(request)
				.orElseThrow(() -> new AppException(AppErrorCode.ACCESS_TOKEN_NOT_FOUND));
		this.authenticationService.logout(email, accessToken, refreshToken);
		SecurityContextHolder.getContext().setAuthentication(null);
		ResponseCookie cookie = securityUtil.clearRefreshToken();
		return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).body(null);
	}

	@ApiMessage("Login google")
	@PostMapping("/login-google-mobile")
	public ResponseEntity<ResponseAuthentication> loginGoogle(@RequestBody RequestLoginGoogleMobileByAuthCode request) {
		UserInfoGoogle profileUserGoogle = googleAuthService.getProfileByAuthCode(request.authCode());
		log.info("profileUserGoogle: {}", profileUserGoogle);
		return login(profileUserGoogle.email());
	}

	@ApiMessage("Login google web")
	@PostMapping("/login-google-web")
	public ResponseEntity<ResponseAuthentication> loginGoogle(@RequestBody RequestLoginGoogleWebByAccessToken request) {
		UserInfoGoogle profileUserGoogle = googleAuthService.getProfileByAccessToken(request.accessToken());
		return login(profileUserGoogle.email());
	}

	@ApiMessage("Verify user")
	@PostMapping("/verify")
	public ResponseEntity<Void> verifyUser(@RequestHeader("email") String email, @RequestBody RequestVerify request) {
		verifyService.verifyOtp(email, request.otp());
		return ResponseEntity.ok().build();
	}

	@ApiMessage("Resend OTP Verify")
	@PostMapping("/verify-resent")
	public ResponseEntity<Void> resendOtp(@RequestHeader("email") String email) {
		verifyService.sendOtp(email);
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<ResponseAuthentication> login(String phoneNumber) {
		ResponseAuthentication response = authenticationService.loginByPhoneNumber(phoneNumber);
		ResponseCookie cookie = securityUtil.updateRefreshToken(response.getRefreshToken());
		return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).body(response);
	}
}
