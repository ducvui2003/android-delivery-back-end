/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:05 AM - 16/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.service.chat;

import com.spring.delivery.model.RoomChat;
import com.spring.delivery.repository.mysql.IRoomChatRepository;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomChatServiceImpl implements IRoomService {
    IRoomChatRepository iRoomChatRepository;

    @Override
    public RoomChat findByIdAndUserId(long roomId, long userId) {
        return iRoomChatRepository.findByIdAndUser_Id(roomId, userId).orElseThrow(() -> new AppException(AppErrorCode.NOT_EXIST));
    }
}
