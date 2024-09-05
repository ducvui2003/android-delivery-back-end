package com.spring.delivery.service.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.token.TokenService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	TokenService tokenService;
	UserMapper userMapper = UserMapper.INSTANCE;
	SecurityUtil securityUtil;

	@Override
	public User register(String idToken, User user) {
		FirebaseToken decodedToken;
		try {
			decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
			System.out.println(decodedToken.toString());
		} catch (FirebaseAuthException e) {
			throw new AppException(AppErrorCode.INVALID_TOKEN);
		}

		checkBeforeRegister(user.getEmail(), user.getPhoneNumber());

		user.setVerified(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public ResponseAuthentication login(String email) {
		User user = this.getUserByEmail(email);

		if (user == null) throw new AppException(AppErrorCode.USER_NOT_FOUND);

		ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);

		String accessToken = securityUtil.createAccessToken(userDTO);

		String refreshToken = securityUtil.createRefreshToken(userDTO);

		return ResponseAuthentication.builder()
				.user(userDTO)
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}


	@Override
	public void logout(String email, String accessToken, String refreshToken) {
		tokenService.saveToken(accessToken, email);
		tokenService.saveToken(refreshToken, email);
	}

	@Override
	public boolean isVerify(String email) {
		return userRepository.existsByEmailAndVerifiedIsTrue(email);
	}

	@Override
	public boolean checkBeforeRegister(String email, String phoneNumber) {
		if (userRepository.existsByEmail(email))
			throw new AppException(AppErrorCode.EMAIL_EXISTED);
		if (userRepository.existsByPhoneNumber(phoneNumber))
			throw new AppException(AppErrorCode.PHONE_NUMBER_EXISTED);

		return true;
	}

	@Override
	public User getUserByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
	}

	@Override
	public ResponseAuthentication loginByPhoneNumber(String phoneNumber) {
		User user = this.getUserByPhoneNumber(phoneNumber);

		if (user == null) throw new AppException(AppErrorCode.USER_NOT_FOUND);

		ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);

		String accessToken = securityUtil.createAccessToken(userDTO);

		String refreshToken = securityUtil.createRefreshToken(userDTO);

		return ResponseAuthentication.builder()
				.user(userDTO)
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}
}
