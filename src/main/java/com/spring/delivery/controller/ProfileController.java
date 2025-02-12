package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.RequestChangeNumberPhone;
import com.spring.delivery.domain.request.address.RequestAddress;
import com.spring.delivery.domain.request.address.RequestUpdateAddress;
import com.spring.delivery.domain.response.ResponseAddress;
import com.spring.delivery.service.address.IAddressService;
import com.spring.delivery.service.phone.INumberPhoneService;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    INumberPhoneService numberPhoneService;
    IAddressService addressService;

    @ApiMessage("Change phone number")
    @PatchMapping("/phone")
    public ResponseEntity<ApiResponse<Void>> changeNumberPhone(@RequestBody @Valid RequestChangeNumberPhone phone) {
        log.info("Change phone number");
        numberPhoneService.changeNumberPhone(phone.phoneNumber());
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Get Address")
    @GetMapping("/address")
    public ResponseEntity<List<ResponseAddress>> getAddress() {
        log.info("Invoked function getAddress in Controller");
        List<ResponseAddress> responseAddresses = addressService.findByUser();
        return ResponseEntity.ok().body(responseAddresses);
    }

    @ApiMessage("Add Address")
    @PostMapping("/address")
    public ResponseEntity<ApiResponse<Void>> changeAddress(@RequestBody RequestAddress requestAddress) {
        log.info("Invoked function changeAddress in Controller with parameter requestChangeAddress: {}", requestAddress);
        addressService.addAddress(requestAddress);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Delete Address")
    @DeleteMapping("/address/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Long id) {
        log.warn("Invoked function deleteAddress in Controller with parameter addressID: {}", id);
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Update Address")
    @PatchMapping("/address")
    public ResponseEntity<ApiResponse<Void>> updateAddress(@RequestBody RequestUpdateAddress requestUpdateAddress) {
        log.info("Invoked function updateAddress in Controller with parameter requestUpdateAddress: {}", requestUpdateAddress);
        addressService.updateAddress(requestUpdateAddress);
        return ResponseEntity.ok().build();
    }
}
