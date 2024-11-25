package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseAddress;
import com.spring.delivery.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    List<ResponseAddress> convertToResponseAddresses(List<Address> addresses);
}
