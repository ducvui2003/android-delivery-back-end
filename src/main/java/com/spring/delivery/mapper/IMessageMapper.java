package com.spring.delivery.mapper;

import com.spring.delivery.domain.Content;
import com.spring.delivery.domain.response.chat.ResponseMessage;
import com.spring.delivery.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMessageMapper {
    @Mapping(source = "updatedAt", target = "time")
    ResponseMessage toResponseMessage(Message message);

    ContentMessage toContentMessage(Content content);
}
