package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.address.RequestAddress;
import com.spring.delivery.domain.request.address.RequestUpdateAddress;
import com.spring.delivery.domain.response.ResponseAddress;
import com.spring.delivery.service.address.IAddressService;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/profile/v1")
public class AddressController {
    IAddressService addressService;

    @ApiMessage("Get Address")
    @GetMapping("/get-address")
    public ResponseEntity<List<ResponseAddress>> getAddress() {
        log.info("Invoked function getAddress in Controller");
        List<ResponseAddress> responseAddresses = addressService.findByUser();
        return ResponseEntity.ok().body(responseAddresses);
    }

    @ApiMessage("Add Address")
    @PostMapping("/add-address")
    public ResponseEntity<ApiResponse<Void>> changeAddress(@RequestBody RequestAddress requestAddress) {
        log.info("Invoked function changeAddress in Controller with parameter requestChangeAddress: {}", requestAddress);
        addressService.addAddress(requestAddress);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Delete Address")
    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Long id) {
        log.warn("Invoked function deleteAddress in Controller with parameter addressID: {}", id);
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Update Address")
    @PatchMapping("/update-address")
    public ResponseEntity<ApiResponse<Void>> updateAddress(@RequestBody RequestUpdateAddress requestUpdateAddress) {
        log.info("Invoked function updateAddress in Controller with parameter requestUpdateAddress: {}", requestUpdateAddress);
        addressService.updateAddress(requestUpdateAddress);
        return ResponseEntity.ok().build();
    }

}
