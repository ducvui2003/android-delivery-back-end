package com.spring.delivery.service.email;

import brevo.ApiException;
import brevoApi.TransactionalEmailsApi;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import com.spring.delivery.util.enums.Template;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
    TransactionalEmailsApi transactionalEmailsApi;
    SendSmtpEmailSender sender;

    @Override
    public void sentWelcome(String to) {
        sent(to, null, Template.WELL_COME);
    }

    @Override
    public void sentResetPassword(String email, String otp) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("code", otp.split(""));
        templateModel.put("email", email);
        sent(email, templateModel, Template.RESET_PASSWORD);
    }


    @Override
    public void sentVerify(String email, String otp) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("code", otp.split(""));
        templateModel.put("email", email);
        this.sent(email, templateModel, Template.VERIFY_EMAIL);
    }

    @Async
    public void sent(String to, Map<String, Object> params, Template template) {
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        SendSmtpEmailTo recipient = new SendSmtpEmailTo();
        recipient.setEmail(to);
        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(Arrays.asList(recipient));
        sendSmtpEmail.setTemplateId(template.getId().longValue());
        sendSmtpEmail.setParams(params);
        try {
            transactionalEmailsApi.sendTransacEmail(sendSmtpEmail);
        } catch (ApiException e) {
            log.error("Error sending email", e);
        }
    }

}
