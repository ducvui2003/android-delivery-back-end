package com.spring.notificationservice.controller;

import com.spring.event.notification.request.TransactionEmailTemplate;
import com.spring.notificationservice.mapper.EmailMapper;
import com.spring.notificationservice.model.Email;
import com.spring.notificationservice.service.http.HttpEmailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class EmailController {
    HttpEmailService httpEmailService;
    EmailMapper emailMapper = EmailMapper.INSTANCE;

    @PostMapping("/template")
    public void sendEmailTemplate(@Valid @RequestBody TransactionEmailTemplate requestTransactionEmailTemplate) {
        Email email = emailMapper.toEmail(requestTransactionEmailTemplate);
        httpEmailService.sendEmail(email);
    }
}
