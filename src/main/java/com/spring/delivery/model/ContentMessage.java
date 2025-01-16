/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 7:21 PM - 15/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "content_messages")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentMessage extends BaseModel {
    String data;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    ContentType type = ContentType.MESSAGE;

    @ManyToOne()
    @JoinColumn(name = "message_id")
    Message message;
}
