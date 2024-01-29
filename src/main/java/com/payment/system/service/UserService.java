package com.payment.system.service;

import com.payment.system.dto.request.SignUpRequest;
import com.payment.system.dto.response.SignUpResponse;
import com.payment.system.entity.UserEntity;
import com.payment.system.exception.AlreadyExistException;
import com.payment.system.exception.NotFoundException;
import com.payment.system.mapper.UserMapper;
import com.payment.system.messaging.MailEvent;
import com.payment.system.messaging.MailPublisher;
import com.payment.system.repository.UserRepository;
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
public class UserService {

    UserMapper userMapper;
    MailPublisher mailPublisher;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public SignUpResponse signup(SignUpRequest signUpRequest) {
        log.info("Sign up process was started : request - {}", signUpRequest);
        var optionalUser = userRepository.findByEmailAndIsActive(signUpRequest.getEmail(),true);
        if (optionalUser.isPresent() && optionalUser.get().isActive()) {
            throw new AlreadyExistException("Phone number has already existed");
        }
        String UID = UUID.randomUUID().toString();
        UserEntity userEntity = userMapper.toUserEntity(signUpRequest);
        userEntity.setActivationId(UID);
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(userEntity);
        String url = String.format("http://localhost:8080/user-activate/%s", UID);
        MailEvent mailEvent = MailEvent.builder()
                .address(signUpRequest.getEmail())
                .message(url).build();
        mailPublisher.sendEvent(mailEvent);
        return new SignUpResponse(url);
    }

    public void activate(String activateId) {
        UserEntity userEntity = userRepository.findByActivationId(activateId)
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        userEntity.setActive(true);
    }
}
