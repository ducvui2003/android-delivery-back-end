package com.spring.delivery.config;

import com.spring.delivery.model.User;
import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component("userDetailsService")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsCustom implements UserDetailsService {
    AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = authenticationService.getUserByPhoneNumber(username);
        if (user == null) throw new AppException(AppErrorCode.USER_NOT_FOUND);

        Collection<GrantedAuthority> authorities = user.getRole().getPermissions().stream().map(permission ->
                new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(),
                authorities);
    }
}
