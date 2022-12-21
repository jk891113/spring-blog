package com.sparta.springblog.responsedto;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Posting;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostingResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String username;
    private String contents;
    private List<CommentResponseDto> commentList;

    public PostingResponseDto(Posting posting) {
        this.createdAt = posting.getCreatedAt();
        this.modifiedAt = posting.getModifiedAt();
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.username = posting.getUser().getUsername();
        this.contents = posting.getContents();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : posting.getCommentList()) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        this.commentList = commentResponseDtoList;
    }
}
