/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:48 AM - 12/08/2024
 * User: lam-nguyen
 **/
package com.spring.delivery.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseGoogleAuthorizationCode(
		@JsonProperty("access_token") String accessToken,
		String scope,
		@JsonProperty("token_type") String tokenType,
		@JsonProperty("id_token") String idToken,
		@JsonProperty("expires_in") Long expiresIn) {}
