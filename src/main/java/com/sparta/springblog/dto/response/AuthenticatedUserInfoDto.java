package com.sparta.springblog.dto.response;

import com.sparta.springblog.enums.UserRoleEnum;
import lombok.Getter;

@Getter
public class AuthenticatedUserInfoDto {
    private UserRoleEnum userRoleEnum;
    private String username;

    public AuthenticatedUserInfoDto(UserRoleEnum userRoleEnum, String username) {
        this.userRoleEnum = userRoleEnum;
        this.username = username;
    }
}
