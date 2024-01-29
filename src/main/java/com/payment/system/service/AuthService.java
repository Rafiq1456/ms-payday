package com.payment.system.service;

import com.payment.system.dto.request.LoginRequest;
import com.payment.system.dto.response.LoginResponse;
import com.payment.system.entity.UserEntity;
import com.payment.system.entity.UserTokenEntity;
import com.payment.system.exception.AuthenticationException;
import com.payment.system.exception.NotFoundException;
import com.payment.system.mapper.UserMapper;
import com.payment.system.repository.UserRepository;
import com.payment.system.repository.UserTokenRepository;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    UserRepository userRepository;

    UserTokenRepository userTokenRepository;

    PasswordEncoder passwordEncoder;

    UserMapper userMapper;

    public LoginResponse login(LoginRequest loginRequest) {

        UserEntity userEntity = userRepository.findByEmailAndIsActive(loginRequest.getEmail(),true)
                .orElseThrow(() -> new NotFoundException("Incorrect email or password"));

        boolean auth = passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword());
        if (!auth) {
            throw new AuthenticationException("Incorrect phone number or password");
        }

        String token = generateRandomToken();
        LoginResponse loginResponse = userMapper.toLoginResponse(userEntity);
        loginResponse.setToken(token);
        UserTokenEntity userTokenEntity=UserTokenEntity.builder()
                .token(token)
                .user(userEntity).build();
        userTokenRepository.save(userTokenEntity);
        return loginResponse;
    }

    private String generateRandomToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
