package com.payment.system.mapper;

import com.payment.system.dto.request.SignUpRequest;
import com.payment.system.dto.response.LoginResponse;
import com.payment.system.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toUserEntity(SignUpRequest signupRequest);

    LoginResponse toLoginResponse(UserEntity userEntity);
}
