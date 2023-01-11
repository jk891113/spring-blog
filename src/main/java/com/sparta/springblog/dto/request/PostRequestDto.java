package com.sparta.springblog.dto.request;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private Long categoryId;
    private String title;
    private String contents;
}
