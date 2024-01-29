package com.payment.system.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    @NotNull
    String name;
    @NotNull
    @Pattern(regexp = "(.+)@(.+)", message = "gmail must be correct regex")
    String email;
    @NotNull
    @Size(min = 6, message = "Minimum 9 character must be entered")
//    @Pattern(regexp = "/^[a-zA-Z0-9]+$/\n", message = "password must be correct regex")
    String password;
}
