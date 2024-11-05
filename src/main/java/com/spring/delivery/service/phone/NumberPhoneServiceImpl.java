package com.spring.delivery.service.phone;

import com.spring.delivery.model.User;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NumberPhoneServiceImpl implements INumberPhoneService {
    UserRepository userRepository;

    @Override
    @Transactional
    public void changeNumberPhone(String phoneNumber) {
        System.out.println(SecurityUtil.getCurrentUserLogin().get());
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().get()).get();
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }
}
