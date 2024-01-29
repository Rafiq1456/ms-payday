package com.payment.system.util;

import com.payment.system.entity.UserEntity;
import com.payment.system.exception.AuthenticationException;
import com.payment.system.security.UserPrincipal;
import java.util.Optional;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtil {

    private static UserPrincipal getUserPrincipal() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserPrincipal) principal;
        }
        return null;
    }

    public static Optional<UserEntity> getCurrentUser() {
        var userPrincipal = getUserPrincipal();
        if (userPrincipal != null) {
            UserEntity user = (UserEntity) userPrincipal.getEntity();
            if (user != null) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static UserEntity getLoginUser() {
        var userPrincipal = getUserPrincipal();
        if (userPrincipal != null) {
            UserEntity user = (UserEntity) userPrincipal.getEntity();
            if (user != null) {
                return user;
            }
        }
        throw new AuthenticationException();
    }
}
