/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:37 AM - 23/08/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.util;

import com.spring.delivery.util.enums.RedisNameSpace;

public class RedisUtil {
	public static String generateKey(RedisNameSpace nameSpace, String... data) {
		return nameSpace.getName() + String.join(":", data);
	}
}
