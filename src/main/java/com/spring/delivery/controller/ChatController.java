package com.spring.delivery.controller;

import com.spring.delivery.domain.request.chat.RequestMessage;
import com.spring.delivery.mapper.IMessageMapper;
import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.service.chat.IMessageService;
import com.spring.delivery.service.chat.IRoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Controller("/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PACKAGE)
public class ChatController {
    SimpMessagingTemplate messagingTemplate;
    IRoomService iRoomService;
    IMessageMapper iMessageMapper;
    IMessageService iMessageService;

    @MessageMapping("/{user_id}/{room_id}")
    public void message(@DestinationVariable("user_id") long userId,
                        @DestinationVariable("room_id") long roomId, RequestMessage requestMessage) {
        try {
            var room = iRoomService.findByIdAndUserId(roomId, userId);
            var time = LocalDateTime.now();
            var message = iMessageService.save(room, requestMessage.content(), time);
            room.getUser().forEach(user -> {
                messagingTemplate.convertAndSend("/message/notify/" + user.getId(), iMessageMapper.toResponseMessage(message));
            });
        } catch (Exception ignored) {
        }
    }
}