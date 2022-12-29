package com.sparta.springblog.entity;

import com.sparta.springblog.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.id.IdentifierGenerationException;

import java.sql.SQLIntegrityConstraintViolationException;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, UserRoleEnum role) throws SQLIntegrityConstraintViolationException {
        this.username = username;
        this.password = password;
        this.role = role;
        if (username == null) {
            throw new IdentifierGenerationException("아이디를 입력하세요.");
        }
        if (password == null) {
            throw new SQLIntegrityConstraintViolationException("비밀번호를 입력하세요.");
        }
    }

//    public boolean isPostingWriter(Posting posting) {
//        return !this.username.equals(posting.getUser().getUsername()) && this.role == UserRoleEnum.USER;
//    }

//    public boolean isCommentWriter(Comment comment) {
//        return !this.username.equals(comment.getUsername()) && this.role == UserRoleEnum.USER;
//    }
}
