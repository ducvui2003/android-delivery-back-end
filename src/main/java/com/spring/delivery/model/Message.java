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
import com.spring.delivery.util.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "messages")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends DeletedModel {
    boolean isRead;

    @OneToMany(mappedBy = "message")
    List<ContentMessage> content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    RoomChat room;
}
