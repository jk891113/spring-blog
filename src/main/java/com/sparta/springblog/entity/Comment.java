package com.sparta.springblog.entity;

import com.sparta.springblog.requestdto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    private Posting posting;

    public Comment(CommentRequestDto requestDto, User user, Posting posting) {
        this.user = user;
        this.posting = posting;
        this.comment = requestDto.getComment();
    }

    public void update(CommentRequestDto responseDto) {
        this.comment = responseDto.getComment();
    }
}
