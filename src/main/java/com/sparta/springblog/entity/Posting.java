package com.sparta.springblog.entity;

import com.sparta.springblog.dto.UpdateRequestDto;
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
    private String writerName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Posting(String writerName, String password, String title, String contents) {
        this.writerName = writerName;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    public void update(UpdateRequestDto requestDto) {
        this.writerName = requestDto.getWriterName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
