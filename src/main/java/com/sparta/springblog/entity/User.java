package com.sparta.springblog.entity;

import com.sparta.springblog.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

//    public boolean isPostingWriter(Posting posting) {
//        return !this.username.equals(posting.getUser().getUsername()) && this.role == UserRoleEnum.USER;
//    }

//    public boolean isCommentWriter(Comment comment) {
//        return !this.username.equals(comment.getUsername()) && this.role == UserRoleEnum.USER;
//    }
}
