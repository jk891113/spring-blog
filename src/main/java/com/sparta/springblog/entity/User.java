package com.sparta.springblog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[0-9a-z]*$", message = "아이디 형식에 맞지 않습니다. 아이디는 4 ~ 10자리 영문(소문자), 숫자로 이루어져야 합니다.")
    @Size(min = 4, max = 10)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9a-zA-Z]*$", message = "비밀번호 형식에 맞지 않습니다. 비밀번호는 8 ~ 15자리 영문(대,소문자), 숫자로 이루어져야 합니다.")
    @Size(min = 8, max = 15)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
