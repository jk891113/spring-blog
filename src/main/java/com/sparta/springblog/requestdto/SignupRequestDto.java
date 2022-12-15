package com.sparta.springblog.requestdto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[0-9a-z]*$", message = "아이디 형식에 맞지 않습니다. 아이디는 4 ~ 10자리 영문(소문자), 숫자로 이루어져야 합니다.")
    @Size(min = 4, max = 10)
    private String username;


    @Pattern(regexp = "^[0-9a-zA-Z]*$", message = "비밀번호 형식에 맞지 않습니다. 비밀번호는 8 ~ 15자리 영문(대,소문자), 숫자로 이루어져야 합니다.")
    @Size(min = 8, max = 15)
    private String password;
}
