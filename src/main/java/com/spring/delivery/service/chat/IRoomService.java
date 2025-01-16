/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:03 AM - 16/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.service.chat;

import com.spring.delivery.model.RoomChat;

public interface IRoomService {
    RoomChat findByIdAndUserId(long roomId, long userId);
}
