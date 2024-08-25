package com.spring.delivery.service.email;

import java.util.Map;

import com.spring.delivery.util.enums.Template;

public interface EmailService {
	void sent(String to, String subject, Map<String, Object> templateModel, Template template);

	void sent(String to, String subject, Map<String, Object> templateModel);
}
