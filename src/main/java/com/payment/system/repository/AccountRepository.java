package com.payment.system.repository;

import com.payment.system.entity.AccountEntity;
import com.payment.system.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByCardIdAndUser(String cardNumber,UserEntity user);
}
