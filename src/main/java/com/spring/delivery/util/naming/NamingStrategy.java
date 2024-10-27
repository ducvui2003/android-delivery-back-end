package com.spring.delivery.util.naming;

@FunctionalInterface
public interface NamingStrategy {
    public String getName(String originalName);
}
