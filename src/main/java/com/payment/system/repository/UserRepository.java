package com.payment.system.repository;

import com.payment.system.entity.UserEntity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByActivationId(String id);

    Optional<UserEntity> findByEmailAndIsActive(String email,boolean isActive);
}
