/**
 * Class: BrevoConfig
 *
 * @author ducvui2003
 * @created 14/10/24
 */
package com.spring.delivery.config;

import brevo.ApiClient;
import brevoApi.TransactionalEmailsApi;
import brevoModel.SendSmtpEmailSender;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrevoConfig {
    @Value("${app.service.brevo.api-key}")
    String brevoApiKey;
    @Value("${app.service.brevo.email}")
    String email;
    @Value("${app.service.brevo.name}")
    String name;

    @Bean
    public TransactionalEmailsApi transactionalEmailsApi() {
        ApiClient defaultClient = brevo.Configuration.getDefaultApiClient();
        defaultClient.setApiKey(brevoApiKey);
        return new TransactionalEmailsApi();
    }

    @Bean
    public SendSmtpEmailSender sendSmtpEmailSender() {
        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail(email);
        sender.setName(name);
        return sender;
    }
}
