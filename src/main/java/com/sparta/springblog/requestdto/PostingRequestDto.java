package com.sparta.springblog.requestdto;

import lombok.Getter;

@Getter
public class PostingRequestDto {
    private Long categoryId;
    private String title;
    private String contents;
}
