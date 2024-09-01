package com.spring.fileservice.util.naming;

@FunctionalInterface
public interface NamingStrategy {
    public String getName(String originalName);
}
