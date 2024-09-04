package com.spring.event.notification.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionEmailTemplate {
    @NotNull
    Receiver[] receiver;
    Receiver[] bcc;
    Receiver[] cc;
    @NotNull
    String template;
    @NotNull
    Map<String, Object> params;
}
