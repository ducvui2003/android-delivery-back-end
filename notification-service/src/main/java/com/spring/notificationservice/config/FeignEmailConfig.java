package com.spring.notificationservice.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FeignEmailConfig implements RequestInterceptor {
    @NonFinal
    @Value("${service.email.api-key}")
    String apiKey;
    ObjectMapper objectMapper;
    @NonFinal
    @Value("${service.email.sender.email}")
    public String email;
    @NonFinal
    @Value("${service.email.sender.name}")
    public String name;

    @Override
    public void apply(RequestTemplate template) {
        addSender(email, name, template);
        template.header("api-key", apiKey);
    }

    public void addSender(String email, String name, RequestTemplate template) {
        try {
            if (template.method().equals("POST") || template.method().equals("PUT")) {

                // Lấy body của request
                JsonNode jsonNode = objectMapper.readTree(template.body());

                // Cast body của request thành ObjectNode
                if (jsonNode.isObject()) {
                    ObjectNode objectNode = (ObjectNode) jsonNode;

                    // Tạo ObjectNode sender
                    ObjectNode sender = objectMapper.createObjectNode();
                    sender.put("email", email);
                    sender.put("name", name);
                    objectNode.set("sender", sender);

                    // Set lại body của request
                    template.body(objectMapper.writeValueAsString(objectNode));
                }
            }
        } catch (Exception e) {
            log.info("Error when adding sender to request: " + e.getMessage());
        }
    }
}
