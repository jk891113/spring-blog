package com.sparta.springblog.entity;

import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Posting extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Posting(PostingRequestDto requestDto, User user) {
        this.username = user.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(UpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
