package com.spring.delivery.config.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.redis.RedisService;
import com.spring.delivery.util.enums.RedisNameSpace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserTask {
	UserRepository userRepository;
	RedisService<String> redisService;

	//    Mỗi 6 tiếng chạy 1 lần
	@Scheduled(cron = "0 0 */6 * * *")
	public void deleteUserNotVerified() {
		log.info("Cron job delete user is not verified started at: {}", System.currentTimeMillis());
		//        Xóa user chưa xác thực trong mysql
		userRepository.deleteByVerifiedFalse();
		//        Xóa verify code trong redis
		redisService.deleteKeysByNameSpace(RedisNameSpace.OTP_EMAIL);
	}
}
