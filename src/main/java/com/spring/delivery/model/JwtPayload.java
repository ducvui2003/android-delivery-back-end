package com.spring.delivery.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JwtPayload {
    String email;
    UserPayload user;
    String role;
    List<String> permissions;
    long timeExpiredPlus;

    @Data
    @Builder
    public static class UserPayload {
        long id;
        String phoneNumber;
        String email;
        String fullName;
    }
}
