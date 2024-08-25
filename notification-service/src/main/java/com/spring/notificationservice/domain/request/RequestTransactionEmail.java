package com.spring.notificationservice.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.notificationservice.model.Receiver;
import com.spring.notificationservice.model.Sender;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTransactionEmail {
    @NotNull
    Sender sender;
    @NotNull
    @JsonProperty("to")
    Receiver[] receiver;
    Receiver[] bcc;
    Receiver[] cc;
    @NonNull
    String textContent;
    @NonNull
    String subject;
    @NonNull
    Receiver replyTo;
}
