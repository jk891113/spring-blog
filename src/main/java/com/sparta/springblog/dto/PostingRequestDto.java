package com.sparta.springblog.dto;

import lombok.Getter;

@Getter
public class PostingRequestDto {
    private String writerName;
    private String password;
    private String title;
    private String contents;

}
