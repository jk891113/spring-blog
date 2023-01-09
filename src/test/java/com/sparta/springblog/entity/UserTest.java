package com.sparta.springblog.entity;

import com.sparta.springblog.enums.UserRoleEnum;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
    @Nested
    @DisplayName("회원 가입한 유저 정보의 객체 생성")
    class CreatedUser {

        private String username;
        private String password;
        private UserRoleEnum role;

        @BeforeEach
        void setup() {
            username = "aaaa";
            password = "1234?5678";
            role = UserRoleEnum.USER;
        }

        @Test
        @DisplayName("정상 케이스")
        void signup_Normal () throws SQLIntegrityConstraintViolationException {
            // Given

            // When
            User user = new User(username, password, role);

            // Then
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
            assertEquals(role, user.getRole());
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases {
            @Nested
            @DisplayName("username")
            class username {
                @Test
                @DisplayName("null")
                void fail() {
                    // given
                    username = null;

                    // when
                    Exception exception = assertThrows(IdentifierGenerationException.class, () -> {
                        new User(username, password, role);
                    });

                    // then
                    assertEquals("아이디를 입력하세요.", exception.getMessage());
                }

                @Test
                @DisplayName("username이 형식에 맞지 않는 경우")
                void fail1() {
                    // given
                    username = "aaa";

                    // when
                    Exception exception = assertThrows(MethodArgumentNotValidException.class, () -> {
                        new User(username, password, role);
                    });

                    // then
                    assertEquals("아이디 형식에 맞지 않습니다. 아이디는 4 ~ 10자리 영문(소문자), 숫자로 이루어져야 합니다.", exception.getMessage());
                }
            }
        }
    }
}