package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.RequestChangeNumberPhone;
import com.spring.delivery.service.phone.INumberPhoneService;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/v1")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NumberPhoneController {
    INumberPhoneService numberPhoneService;

    @ApiMessage("Change phone number")
    @PatchMapping("/change-phone")
    public ResponseEntity<ApiResponse<Void>> changeNumberPhone(@RequestBody @Valid RequestChangeNumberPhone phone) {
        log.info("Change phone number");
        numberPhoneService.changeNumberPhone(phone.phoneNumber());
        return ResponseEntity.ok().build();
    }
}
