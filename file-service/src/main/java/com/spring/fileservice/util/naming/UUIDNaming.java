package com.spring.fileservice.util.naming;


import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UUIDNaming implements NamingStrategy {
    @Override
    public String getName(String originalName) {
        return UUID.randomUUID().toString() + "_" + originalName;
    }
}
