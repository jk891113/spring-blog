package com.sparta.springblog.requestdto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private Long categoryId;
    private String title;
    private String contents;
}
