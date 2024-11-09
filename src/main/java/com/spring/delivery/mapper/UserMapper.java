package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.request.RequestUpdateProfile;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.model.Address;
import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(RequestRegister userRequest);

    ResponseAuthentication.UserDTO toUserDTO(User user);

    JwtPayload.UserPayload toUserPayload(User user);

    @Mapping(source = "address", target = "address", qualifiedByName = "stringToAddressSet")
    User toUser(RequestUpdateProfile requestUpdateProfile);

    // Phương thức phụ để map String thành Set<Address>
    @Named("stringToAddressSet")
    default Set<Address> stringToAddressSet(String address) {
        if (address == null) {
            return Collections.emptySet();
        }
        Address newAddress = new Address();
        newAddress.setAddress(address);
        return Set.of(newAddress);
    }
}
