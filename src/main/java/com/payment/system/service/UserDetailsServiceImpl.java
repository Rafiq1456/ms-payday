package com.payment.system.service;


import com.payment.system.constant.Role;
import com.payment.system.entity.UserEntity;
import com.payment.system.entity.UserTokenEntity;
import com.payment.system.repository.UserTokenRepository;
import com.payment.system.security.UserPrincipal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserTokenRepository userTokenRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Optional<UserTokenEntity> optionalTokenEntity = userTokenRepository.findById(token);
        if (optionalTokenEntity.isPresent()) {
            UserEntity user = optionalTokenEntity.get().getUser();
            return new UserPrincipal(user, Role.ROLE_USER);
        }
        return null;
    }
}
