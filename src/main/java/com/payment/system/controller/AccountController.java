package com.payment.system.controller;

import static com.payment.system.constant.AuthRole.ROLE_USER;

import com.payment.system.dto.request.AccountRequest;
import com.payment.system.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    @PutMapping
    @PreAuthorize(ROLE_USER)
    @ResponseStatus(HttpStatus.OK)
    void addCashToAccount(@RequestBody AccountRequest accountRequest){
        accountService.addCashToAccount(accountRequest);
    }

    AccountService accountService;
}
