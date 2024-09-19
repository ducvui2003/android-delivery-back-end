package com.spring.delivery.service.authentication;

import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.model.Permission;
import com.spring.delivery.model.Role;
import com.spring.delivery.repository.RoleRepository;
import com.spring.delivery.util.enums.AuthType;
import com.spring.delivery.util.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.service.token.TokenService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TokenService tokenService;
    UserMapper userMapper = UserMapper.INSTANCE;
    SecurityUtil securityUtil;
    RoleRepository roleRepository;

    @Override
    public User register(String idToken, User user) {
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            log.info("decoded token {}", decodedToken);
        } catch (FirebaseAuthException e) {
            throw new AppException(AppErrorCode.INVALID_TOKEN);
        }

        checkBeforeRegister(user.getEmail(), user.getPhoneNumber());

        user.setVerified(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ResponseAuthentication getAccessToken(String email) {
        User user = this.getUserByEmail(email);

        if (user == null) throw new AppException(AppErrorCode.USER_NOT_FOUND);

        ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);

        JwtPayload jwtPayload = getJwtPayload(user);

        String accessToken = securityUtil.createAccessToken(jwtPayload);

        tokenService.saveToken(user.getEmail(), accessToken);

        String refreshToken = securityUtil.createRefreshToken(jwtPayload);

        return ResponseAuthentication.builder()
                .user(userDTO)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public void logout(String email, String accessToken, String refreshToken) {
        tokenService.saveToken(accessToken, email);
        tokenService.saveToken(refreshToken, email);
    }

    @Override
    public boolean isVerify(String email) {
        return userRepository.existsByEmailAndVerifiedIsTrue(email);
    }

    @Override
    public boolean checkBeforeRegister(String email, String phoneNumber) {
        if (userRepository.existsByEmail(email))
            throw new AppException(AppErrorCode.EMAIL_EXISTED);
        if (userRepository.existsByPhoneNumber(phoneNumber))
            throw new AppException(AppErrorCode.PHONE_NUMBER_EXISTED);

        return true;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }

    @Override
    public ResponseAuthentication loginByPhoneNumber() {
//        Không cần case TH user không tồn tại vì đã được xử lý ở phần loadUserDetail
        String phoneNumber = SecurityUtil.getCurrentUserLogin().get();
        log.info("loginByPhoneNumber {}", phoneNumber);

        User user = this.getUserByPhoneNumber(phoneNumber);

        ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);
        JwtPayload jwtPayload = getJwtPayload(user);

        String accessToken = securityUtil.createAccessToken(jwtPayload);

        String refreshToken = securityUtil.createRefreshToken(jwtPayload);

        return ResponseAuthentication.builder()
                .user(userDTO)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public ResponseAuthentication loginByEmail() {
//        Không cần case TH user không tồn tại vì đã được nạp vào context
        String email = SecurityUtil.getCurrentUserLogin().get();
        log.info("email {}", email);

        User user = this.getUserByEmail(email);

        ResponseAuthentication.UserDTO userDTO = userMapper.toUserDTO(user);
        JwtPayload jwtPayload = getJwtPayload(user);

        String accessToken = securityUtil.createAccessToken(jwtPayload);

        String refreshToken = securityUtil.createRefreshToken(jwtPayload);

        return ResponseAuthentication.builder()
                .user(userDTO)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void createUserOAuth2(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        if (userRepository.existsByEmail(email)) return;

        Role roleUser = roleRepository.findByName(RoleEnum.USER);
        User user = User.builder()
                .email(email)
                .fullName(oAuth2User.getAttribute("name"))
                .verified(true)
                .role(roleUser)
                .authType(AuthType.OAUTH2)
                .build();
        userRepository.save(user);
    }

    private JwtPayload getJwtPayload(User user) {
        return JwtPayload.builder()
                .email(user.getEmail())
                .user(userMapper.toUserPayload(user))
                .role(user.getRole().getName().name())
                .permissions(user.getRole().getPermissions().stream().map(Permission::getName).toList())
                .timeExpiredPlus(1)
                .build();
    }
}
