package com.payment.system.service;

import static org.mockito.Mockito.when;

import com.payment.system.dto.request.SignUpRequest;
import com.payment.system.dto.response.SignUpResponse;
import com.payment.system.entity.UserEntity;
import com.payment.system.exception.AlreadyExistException;
import com.payment.system.mapper.UserMapper;
import com.payment.system.messaging.MailPublisher;
import com.payment.system.repository.UserRepository;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    MailPublisher mailPublisher;
    @InjectMocks
    UserService userService;

    @Test
    void signup_Success() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("string@gmail.com")
                .password("string")
                .name("string").build();

        UserEntity userEntity = UserEntity.builder().build();

        when(userMapper.toUserEntity(signUpRequest)).thenReturn(userEntity);
        SignUpResponse actual = userService.signup(signUpRequest);
    }
    @Test
    void signup_Fail() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("string@gmail.com")
                .password("string")
                .name("string").build();

        UserEntity userEntity = UserEntity.builder()
                .isActive(true)
                .build();
        when(userRepository.findByEmailAndIsActive(signUpRequest.getEmail(),true))
                .thenReturn(Optional.of(userEntity));

        assertThrows(AlreadyExistException.class, ()-> userService.signup(signUpRequest));
    }


}
