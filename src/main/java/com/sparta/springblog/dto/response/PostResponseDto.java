package com.sparta.springblog.dto.response;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PostResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private Long categoryId;
    private String title;
    private String username;
    private String contents;
    private List<CommentResponseDto> commentList;

    public PostResponseDto(Post post, List<Comment> commentList) {
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.id = post.getId();
        this.categoryId = post.getCategoryId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.contents = post.getContents();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            if (Objects.equals(comment.getPost().getId(), post.getId()) && comment.getId() == 0)
                commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        this.commentList = commentResponseDtoList;
    }
}
