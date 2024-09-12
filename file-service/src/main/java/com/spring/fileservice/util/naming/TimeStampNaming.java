package com.spring.fileservice.util.naming;

public class TimeStampNaming implements NamingStrategy {
    @Override
    public String getName(String originalName) {
        return System.currentTimeMillis() + "_" + originalName;
    }
}
