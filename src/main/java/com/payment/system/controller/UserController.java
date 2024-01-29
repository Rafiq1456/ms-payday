package com.payment.system.controller;

import com.payment.system.dto.request.SignUpRequest;
import com.payment.system.dto.response.SignUpResponse;
import com.payment.system.service.UserService;
import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    SignUpResponse signup(@Valid @RequestBody SignUpRequest signupRequest) {
        return userService.signup(signupRequest);
    }


    @PutMapping("/user-active/{id}")
    void activate(@PathVariable String id){
         userService.activate(id);
    }

}
