package com.spring.delivery.util;

import com.spring.delivery.domain.response.UserInfoGoogle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@AllArgsConstructor
@Getter
public class CustomOAuth2User implements OAuth2User {
    private final UserInfoGoogle userInfoGoogle;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of("email", userInfoGoogle.email(),
                "name", userInfoGoogle.name(),
                "picture", userInfoGoogle.picture(),
                "sub", userInfoGoogle.sub());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getName() {
        return userInfoGoogle.name();
    }
}
