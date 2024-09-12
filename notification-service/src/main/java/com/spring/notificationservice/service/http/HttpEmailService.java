package com.spring.notificationservice.service.http;

import com.spring.notificationservice.config.FeignEmailConfig;
import com.spring.notificationservice.model.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "${service.email.base-url}/smtp/email", configuration = FeignEmailConfig.class)
public interface HttpEmailService {
    @PostMapping(headers = {
            "Content-Type=application/json",
            "Accept=application/json"
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    void sendEmail(@RequestBody Email email);

}
