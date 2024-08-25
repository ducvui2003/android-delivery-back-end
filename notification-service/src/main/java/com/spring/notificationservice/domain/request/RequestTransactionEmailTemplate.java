package com.spring.notificationservice.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.notificationservice.model.Receiver;
import com.spring.notificationservice.model.Sender;
import com.spring.notificationservice.util.constrant.TEMPLATE;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTransactionEmailTemplate {
    @NotNull
    Sender sender;
    @NotNull
    @JsonProperty("to")
    Receiver[] receiver;
    Receiver[] bcc;
    Receiver[] cc;
    @NonNull
    Receiver replyTo;
    @NonNull
    TEMPLATE template;
    @NonNull
    Map<String, Object> params;
}
