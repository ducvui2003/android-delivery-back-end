package com.spring.event.notification.request;

import com.spring.notificationservice.util.anotation.EnumValid;
import com.spring.notificationservice.util.constrant.TEMPLATE;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionEmailTemplate {
    @NotNull
    Receiver[] receiver;
    Receiver[] bcc;
    Receiver[] cc;
    @NotNull
    @EnumValid(enumClass = TEMPLATE.class)
    String template;
    @NotNull
    Map<String, Object> params;
}
