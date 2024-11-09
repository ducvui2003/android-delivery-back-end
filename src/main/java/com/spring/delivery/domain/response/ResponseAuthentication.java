package com.spring.delivery.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseAuthentication {
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
    public record UserDTO(long id, String phoneNumber, String email, String fullName, String role) {
        public static UserDTO initFromMapInfoUserDTO(Map<String, Object> mapUser) {
            return UserDTO.builder()
                    .id((Long) mapUser.get("id"))
                    .phoneNumber((String) mapUser.get("phoneNumber"))
                    .email((String) mapUser.get("email"))
                    .role((String) mapUser.get("role"))
                    .fullName((String) mapUser.get("fullName"))
                    .build();
        }
    }
}
