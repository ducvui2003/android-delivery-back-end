package com.spring.delivery.domain.request.chat;

import com.spring.delivery.domain.Content;
import lombok.Builder;

import java.util.List;

@Builder
public record RequestMessage(
        List<Content> content
) {
}