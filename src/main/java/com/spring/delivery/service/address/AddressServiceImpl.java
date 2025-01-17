package com.spring.delivery.service.address;

import com.spring.delivery.domain.request.address.RequestAddress;
import com.spring.delivery.domain.request.address.RequestUpdateAddress;
import com.spring.delivery.domain.response.ResponseAddress;
import com.spring.delivery.mapper.AddressMapper;
import com.spring.delivery.model.Address;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.mysql.IAddressRepository;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressServiceImpl implements IAddressService {
    UserRepository userRepository;
    IAddressRepository addressRepository;
    AddressMapper addressMapper;

    @Override
    public List<ResponseAddress> findByUser() {
        System.out.println(SecurityUtil.getCurrentUserLogin());
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().get()).get();
        System.out.println(user);
        List<Address> addresses = addressRepository.findByUserId(user.getId());
        return addressMapper.mapListToListResponseAddresses(addresses);
    }

    @Override
    public void addAddress(RequestAddress requestAddress) {
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new AppException(AppErrorCode.UNAUTHORIZED))).orElseThrow(() -> new AppException(AppErrorCode.UNAUTHORIZED));
        int numberOfAddress = addressRepository.countByUser(user);
        if (numberOfAddress >= 5) {
            throw new AppException(AppErrorCode.ADDRESS_FULL);
        }
        Address address = Address.builder()
                .address(requestAddress.address())
                .user(user)
                .build();
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Long addressID) {
        addressRepository.deleteById(addressID);
    }

    @Override
    @Transactional
    public void updateAddress(RequestUpdateAddress requestUpdateAddress) {
        Address address = addressRepository.findById(requestUpdateAddress.id()).get();
        address.setAddress(requestUpdateAddress.address());
        addressRepository.save(address);
    }

    @Override
    public ResponseAddress setDefaultAddress(long id) {
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new AppException(AppErrorCode.UNAUTHORIZED))).orElseThrow(() -> new AppException(AppErrorCode.UNAUTHORIZED));
        addressRepository.findByUser_IdAndIsDefaultTrue(user.getId()).forEach(address -> {
            address.setDefault(false);
            addressRepository.save(address);
        });
        Address address = addressRepository.findByIdAndUser_Id(id, user.getId()).orElseThrow(() -> new AppException(AppErrorCode.NOT_EXIST));
        address.setDefault(true);
        addressRepository.save(address);
        return addressMapper.mapToResponseAddresses(address);
    }
}
