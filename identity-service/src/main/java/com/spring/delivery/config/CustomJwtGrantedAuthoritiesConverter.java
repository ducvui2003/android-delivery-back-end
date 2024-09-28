package com.spring.delivery.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = defaultGrantedAuthoritiesConverter.convert(jwt);

        // Custom logic to extract from "authorities" claim or "permissions" claim
        Collection<GrantedAuthority> authoritiesFromCustomClaim = extractAuthoritiesFromClaim(jwt, "role");
        Collection<GrantedAuthority> permissionsFromCustomClaim = extractAuthoritiesFromClaim(jwt, "permissions");

        // Combine all authorities
        Set<GrantedAuthority> combinedAuthorities = new HashSet<>(grantedAuthorities);
        combinedAuthorities.addAll(authoritiesFromCustomClaim);
        combinedAuthorities.addAll(permissionsFromCustomClaim);

        return combinedAuthorities;
    }

    private Collection<GrantedAuthority> extractAuthoritiesFromClaim(Jwt jwt, String claimName) {
        Collection<String> authorities = jwt.getClaimAsStringList(claimName);
        if (authorities == null) {
            return Set.of();
        }
        return authorities.stream()
                .map(auth -> claimName.equals("role") ? "ROLE_" + auth : auth)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
