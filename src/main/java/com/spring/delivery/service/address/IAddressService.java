package com.spring.delivery.service.address;

import com.spring.delivery.domain.request.address.RequestAddress;
import com.spring.delivery.domain.request.address.RequestUpdateAddress;
import com.spring.delivery.domain.response.ResponseAddress;
import com.spring.delivery.model.Address;

import java.util.List;

public interface IAddressService {
    List<ResponseAddress> findByUser( );

    void addAddress(RequestAddress requestAddress);

    void deleteAddress(Long addressID);

    void updateAddress(RequestUpdateAddress requestUpdateAddress);
}
