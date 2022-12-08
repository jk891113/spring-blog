package com.sparta.springblog.dto;

import com.sparta.springblog.entity.Posting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponseDto {
    private String title;
    private String writerName;
    private String password;
    private String contents;

    public void getPostingForCreate(Posting posting) {
        this.title = posting.getTitle();
        this.password = posting.getPassword();
        this.writerName = posting.getWriterName();
        this.contents = posting.getContents();
    }
}
