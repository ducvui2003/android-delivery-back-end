package com.spring.delivery.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("securityProperties")
@ConfigurationProperties(prefix = "spring.security.allowed")
public class SecurityProperties {
	private List<String> origins;
	private List<String> methods;
	private List<String> headers;

}
