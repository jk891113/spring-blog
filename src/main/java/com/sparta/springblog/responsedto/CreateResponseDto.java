package com.sparta.springblog.responsedto;

import com.sparta.springblog.entity.Posting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponseDto {
    private Long id;
    private String title;
    private String writerName;
    private String password;
    private String contents;

    public CreateResponseDto(Posting posting) {
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.password = posting.getPassword();
        this.writerName = posting.getWriterName();
        this.contents = posting.getContents();
    }
}
