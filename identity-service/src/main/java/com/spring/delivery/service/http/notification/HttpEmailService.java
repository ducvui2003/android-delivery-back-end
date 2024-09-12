package com.spring.delivery.service.http.notification;

import com.spring.event.notification.request.TransactionEmailTemplate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-email-service", url = "${app.service.notification.url}/api/v1/email")
public interface HttpEmailService {
    @PostMapping(value = "/template")
    void sendEmail(@RequestBody TransactionEmailTemplate transactionEmailTemplate);
}
