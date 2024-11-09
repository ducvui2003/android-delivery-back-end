package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.model.JwtPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(RequestRegister userRequest);

    @Mapping(target = "role", source = "role.name")
    ResponseAuthentication.UserDTO toUserDTO(User user);

    JwtPayload.UserPayload toUserPayload(User user);

}
