package com.spring.event.notification.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionEmail {
    @NotNull
    Receiver[] receiver;
    Receiver[] bcc;
    Receiver[] cc;
    @NotNull
    String textContent;
    @NotNull
    String subject;
    @NotNull
    Receiver replyTo;
}