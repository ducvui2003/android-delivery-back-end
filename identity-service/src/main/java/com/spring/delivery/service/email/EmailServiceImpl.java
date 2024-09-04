package com.spring.delivery.service.email;

import com.spring.delivery.service.http.notification.HttpEmailService;
import com.spring.delivery.util.enums.Template;
import com.spring.event.notification.request.Receiver;
import com.spring.event.notification.request.TransactionEmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
    HttpEmailService httpEmailService;

    @Override
    public void sentWelcome(String to) {
        sent(to, null, Template.WEL_COME);
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
    public void sent(String to, Map<String, Object> params, Template templateName) {
        TransactionEmailTemplate transactionEmailTemplate = TransactionEmailTemplate.builder()
                .receiver(new Receiver[]{Receiver.builder().email(to).build()})
                .template(templateName.name())
                .params(params)
                .build();
        httpEmailService.sendEmail(transactionEmailTemplate);
    }

}
