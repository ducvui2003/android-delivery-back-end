package com.spring.delivery.domain.response.chat;

import com.spring.delivery.domain.Content;
import com.spring.delivery.model.RoomChat;
import com.spring.delivery.model.User;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ResponseMessage(
        List<Content> content,
        UserInRoot user,
        Room room,
        Instant time,
        boolean isRead
) {
    public static ResponseMessage init(User user, RoomChat room, List<Content> message, Instant time, boolean isRead) {
        return ResponseMessage.builder()
                .user(UserInRoot.builder().id(user.getId()).avatar(user.getAvatar()).fullName(user.getAvatar()).build())
                .room(ResponseMessage.Room.builder().id(room.getId()).build())
                .content(message)
                .time(time)
                .isRead(isRead)
                .build();
    }

    @Builder
    public record UserInRoot(
            long id,
            String avatar,
            String fullName
    ) {
    }

    @Builder
    public record Room(
            long id
    ) {
    }
}