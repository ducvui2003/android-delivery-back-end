package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.request.RequestUpdateProfile;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.model.Address;
import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.model.Permission;
import com.spring.delivery.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User toUser(RequestRegister userRequest);

    @Mapping(source = "role.name", target = "role")
    @Mapping(source = "permissions", target = "permissions", qualifiedByName = "stringToPermissionsSet")
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

    @Named("stringToPermissionsSet")
    default List<String> stringToPermissionsSet(Set<Permission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return Collections.emptyList();
        }
        return permissions.stream().map(Permission::getName).collect(Collectors.toList());
    }
}
