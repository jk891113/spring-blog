package com.sparta.springblog.responsedto;

import com.sparta.springblog.entity.Posting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponseDto {
    private Long id;
    private String title;
    private String username;
    private String password;
    private String contents;

    public CreateResponseDto(Posting posting) {
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.password = posting.getPassword();
        this.username = posting.getUsername();
        this.contents = posting.getContents();
    }
}
