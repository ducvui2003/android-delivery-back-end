package com.spring.delivery;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DeliveryApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(PhoneNumberUtil.getInstance().getCountryCodeForRegion("VN"));
//        System.out.println(1);
    }
}
