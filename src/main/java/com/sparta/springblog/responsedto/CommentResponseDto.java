package com.sparta.springblog.responsedto;

import com.sparta.springblog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUsername();
        this.comment = comment.getComment();
    }
}
