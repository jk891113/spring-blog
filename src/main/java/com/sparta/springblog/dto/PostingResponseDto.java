package com.sparta.springblog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostingResponseDto {
    private String title;
    private String writerName;
    private String contents;
    private LocalDateTime createdAt;
}
