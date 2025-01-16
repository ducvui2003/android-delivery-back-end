package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.ResponseAddress;
import com.spring.delivery.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    List<ResponseAddress> convertToResponseAddresses(List<Address> addresses);
}
