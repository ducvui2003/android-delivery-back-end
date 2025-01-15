/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:22 AM - 14/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.request.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record RequestLogout(
        @NotBlank(message = "Refresh token not blank") @JsonProperty("refresh_token") String refreshToken,
        @NotBlank(message = "Access token not blank") @JsonProperty("access_token") String accessToken
) {
}
