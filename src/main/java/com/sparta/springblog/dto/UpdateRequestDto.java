package com.sparta.springblog.dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private String title;
    private String writerName;
    private String contents;
}
