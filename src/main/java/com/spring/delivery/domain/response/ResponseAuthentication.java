package com.spring.delivery.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.delivery.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseAuthentication {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonIgnore
    private String refreshToken;

    private UserDTO user;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserGetAccount {
        private UserDTO user;
    }

    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record UserDTO(
            long id,
            String phoneNumber,
            int countryCode,
            String email,
            String fullName,
            String role,
            ResponseAddress address,
            List<String> permissions) {
        public static UserDTO initFromMapInfoUserDTO(Map<String, Object> mapUser) {
            return UserDTO.builder()
                    .id((Long) mapUser.get("id"))
                    .phoneNumber((String) mapUser.get("phoneNumber"))
                    .email((String) mapUser.get("email"))
                    .fullName((String) mapUser.get("fullName"))
                    .role((String) mapUser.get("role"))
                    .permissions((List<String>) mapUser.get("permissions"))
                    .build();
        }
    }
}
