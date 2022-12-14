package com.sparta.springblog.requestdto;

import lombok.Getter;

@Getter
public class PostingRequestDto {
    private String username;
    private String password;
    private String title;
    private String contents;
}
