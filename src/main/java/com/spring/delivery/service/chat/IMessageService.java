/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:43 AM - 16/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.service.chat;

import com.spring.delivery.domain.Content;
import com.spring.delivery.model.Message;
import com.spring.delivery.model.RoomChat;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessageService {
    Message save(RoomChat room, List<Content> message, LocalDateTime time);
}
