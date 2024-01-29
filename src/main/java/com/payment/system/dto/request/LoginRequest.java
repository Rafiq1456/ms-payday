package com.payment.system.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = "Email must not be empty")
    String email;

    @NotBlank(message = "Password must not be empty")
    String password;
}
