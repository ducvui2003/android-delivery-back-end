package com.spring.delivery.util;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import com.spring.delivery.config.properties.JwtProperties;
import com.spring.delivery.domain.response.ResponseAuthentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityUtil {
	JwtEncoder jwtEncoder;
	JwtDecoder jwtDecoder;
	JwtProperties jwtProperties;

	@Value("${app.cookie.key.refreshToken}")
	@NonFinal
	String cookieRefreshToken;

	Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	public String createAccessToken(ResponseAuthentication.UserDTO userDTO) {
		JwtClaimsSet claims = generateClaims(userDTO.email(), userDTO, jwtProperties.getAccessTokenExpiredTime());
		JwsHeader header = JwsHeader.with(JWT_ALGORITHM).build();
		return this.jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}

	public String createRefreshToken(ResponseAuthentication.UserDTO userDTO) {
		JwtClaimsSet claims = generateClaims(userDTO.email(), userDTO, jwtProperties.getRefreshTokenExpiredTime());
		JwsHeader header = JwsHeader.with(JWT_ALGORITHM).build();
		return this.jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}

	public void validateRefreshToken(String refreshToken) {
		try {
			jwtDecoder.decode(refreshToken);
		} catch (JwtException e) {
			logger.error("JWT error {} ", e.getMessage());
			throw new JwtException("Invalid refresh token");
		}
	}

	public ResponseCookie updateRefreshToken(String refreshToken) {
		return ResponseCookie.from(cookieRefreshToken, refreshToken)
				.httpOnly(true)
				.path("/")
				.maxAge(jwtProperties.getRefreshTokenExpiredTime())
				.build();
	}

	public ResponseCookie clearRefreshToken() {
		return ResponseCookie.from(cookieRefreshToken)
				.httpOnly(true)
				.path("/")
				.maxAge(0)
				.build();
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user.
	 */
	public static Optional<String> getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
	}

	private static String extractPrincipal(Authentication authentication) {
		if (authentication == null) return null;

		return switch (authentication.getPrincipal()) {
			case UserDetails springSecurityUser -> springSecurityUser.getUsername();
			case Jwt jwt -> jwt.getSubject();
			case String s -> s;
			default -> null;
		};
	}

	private JwtClaimsSet generateClaims(String email, ResponseAuthentication.UserDTO userDTO, long timeExpiredPlus) {
		Instant now = Instant.now();
		Instant validity = now.plus(timeExpiredPlus, ChronoUnit.SECONDS);
		return JwtClaimsSet.builder()
				.issuedAt(now)
				.expiresAt(validity)
				.subject(email)
				.claim("user", userDTO)
				.build();
	}

	/**
	 * Get the JWT in header request
	 *
	 * @return the JWT in header request
	 */
	public static Optional<String> getAccessTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX))
			return Optional.of(bearerToken.substring(BEARER_PREFIX.length()));

		return Optional.empty();
	}

	public Instant getExpirationInstant(String token) {
		try {
			Jwt jwt = jwtDecoder.decode(token);
			return jwt.getExpiresAt();
		} catch (JwtException e) {
			// Handle decoding errors or invalid JWTs
			throw new JwtException("Invalid refresh token");
		}
	}

	/**
	 * Get the Access Token in Spring Security Context
	 *
	 * @return access token
	 */
	public String getAccessToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof JwtAuthenticationToken jwtToken) {
			Jwt jwt = jwtToken.getToken();
			return jwt.getTokenValue(); // This returns the raw JWT token
		}
		return null;
	}

	public long remainderTimeToken(String token) {
		Instant now = Instant.now();
		Instant createdToken = getExpirationInstant(token);
		Duration duration = Duration.between(now, createdToken);
		return duration.getSeconds();
	}
}
