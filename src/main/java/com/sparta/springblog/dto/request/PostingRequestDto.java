package com.sparta.springblog.dto.request;

import lombok.Getter;

@Getter
public class PostingRequestDto {
    private Long categoryId;
    private String title;
    private String contents;
}
