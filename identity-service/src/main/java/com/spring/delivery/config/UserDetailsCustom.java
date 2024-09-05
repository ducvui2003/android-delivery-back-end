package com.spring.delivery.config;

import com.spring.delivery.model.User;
import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.spring.delivery.util.enums.RoleEnum.ROLE_USER;

@Component("userDetailsService")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsCustom implements UserDetailsService {
	AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = authenticationService.getUserByPhoneNumber(username);
		if (user == null) throw new AppException(AppErrorCode.USER_NOT_FOUND);
		return new org.springframework.security.core.userdetails.User(
				user.getPhoneNumber(),
				user.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(ROLE_USER.name())));
	}
}
