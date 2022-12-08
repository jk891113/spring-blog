package com.sparta.springblog.dto;

import com.sparta.springblog.entity.Posting;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostingResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String writerName;
    private String contents;

    public PostingResponseDto(Posting posting) {
        this.createdAt = posting.getCreatedAt();
        this.modifiedAt = posting.getModifiedAt();
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.writerName = posting.getWriterName();
        this.contents = posting.getContents();
    }
}
