/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 6:51 PM - 15/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "room_chats")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomChat extends DeletedModel {
    @OneToMany(mappedBy = "room")
    List<UserInRomChat> user;

    @OneToMany(mappedBy = "room")
    List<Message> message;
}
