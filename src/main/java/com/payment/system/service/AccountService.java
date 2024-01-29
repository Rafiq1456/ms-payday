package com.payment.system.service;

import com.payment.system.dto.request.AccountRequest;
import com.payment.system.entity.AccountEntity;
import com.payment.system.entity.UserEntity;
import com.payment.system.exception.BadRequestException;
import com.payment.system.repository.AccountRepository;
import com.payment.system.util.SecurityUtil;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    AccountRepository accountRepository;
    @Transactional
    public void addCashToAccount(AccountRequest accountRequest) {
        Optional<UserEntity> currentUser = SecurityUtil.getCurrentUser();
        if(currentUser.isPresent()) {
            Optional<AccountEntity> accountEntity =
                    accountRepository.findByCardIdAndUser(accountRequest.getCardId(), currentUser.get());
            Long oldBalance=accountEntity.get().getBalance();
            Long newQuantity=accountRequest.getAmount();
            accountEntity.get().setBalance(oldBalance+newQuantity);
            return;
        }
        throw new BadRequestException("Bad request");
    }
}
