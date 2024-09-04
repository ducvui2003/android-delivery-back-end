package com.spring.delivery.service.email;

public interface EmailService {

    void sentWelcome(String to);

    void sentVerify(String to, String otp);

    void sentResetPassword(String to, String otp);
}
