/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:52 AM - 16/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.service.chat;

import com.spring.delivery.domain.Content;
import com.spring.delivery.mapper.IMessageMapper;
import com.spring.delivery.model.ContentMessage;
import com.spring.delivery.model.Message;
import com.spring.delivery.model.RoomChat;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.mysql.IContentMessageRepository;
import com.spring.delivery.repository.mysql.IMessageRepository;
import com.spring.delivery.repository.mysql.IRoomChatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl implements IMessageService {
    IMessageRepository iMessageRepository;
    IContentMessageRepository iContentMessageRepository;
    IMessageMapper messageMapper;

    @Override
    public Message save(RoomChat room, List<Content> contents, LocalDateTime time) {
        var message = new Message() {{
            setCreatedAt(Instant.from(time));
            setUpdatedAt(Instant.from(time));
            setRoom(room);
            setRead(false);
        }};
        iMessageRepository.save(message);
        contents.forEach(content -> {
            var contentMessage = messageMapper.toContentMessage(content);
            contentMessage.setMessage(message);
            iContentMessageRepository.save(contentMessage);
        });
        return message;
    }
}
