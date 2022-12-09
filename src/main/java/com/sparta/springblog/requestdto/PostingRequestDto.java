package com.sparta.springblog.requestdto;

import lombok.Getter;

@Getter
public class PostingRequestDto {
    private String writerName;
    private String password;
    private String title;
    private String contents;
}
