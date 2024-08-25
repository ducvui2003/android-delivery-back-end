/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:03 AM - 23/08/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.opt;

import com.spring.delivery.util.enums.RedisNameSpace;

public interface OTPService {
	void verifyOTP(RedisNameSpace nameSpace, String email, String otp);

	String createOPT(RedisNameSpace nameSpace, String email);
}
