package com.spring.delivery.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component("securityProperties")
@ConfigurationProperties(prefix = "spring.security.allowed")
public class SecurityProperties {
	private List<String> origins;
	private List<String> methods;
	private List<String> headers;

}
