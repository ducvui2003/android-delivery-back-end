package com.spring.delivery.service.email;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.spring.delivery.util.enums.Template;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
	JavaMailSender javaMailSender;
	SpringTemplateEngine templateEngine;

	@Async
	@Override
	public void sent(String to, String subject, Map<String, Object> templateModel) {
		this.sent(to, subject, templateModel, Template.VERIFY_EMAIL);
	}

	@Override
	public void sent(String to, String subject, Map<String, Object> templateModel, Template templateName) {
		Context context = new Context();
		context.setVariables(templateModel);
		String view = templateEngine.process(templateName.getName(), context);
		this.sendMailAsync(to, subject, view, true, true);
	}

	private void sendMailAsync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
			message.setTo(to);
			message.setSubject(subject);
			message.setText(content, isHtml);
			this.javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
