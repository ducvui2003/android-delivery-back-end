/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 6:53 PM - 15/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "user_in_rom_chat")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInRomChat extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "room_id")
    RoomChat room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
