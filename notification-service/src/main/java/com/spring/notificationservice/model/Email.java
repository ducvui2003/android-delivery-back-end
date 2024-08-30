package com.spring.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Email {
    @JsonProperty("to")
    Receiver[] receiver;
    Receiver[] bcc;
    Receiver[] cc;
    String textContent;
    String subject;
    Receiver replyTo;
    Integer templateId;
    Map<String, Object> params;
}
