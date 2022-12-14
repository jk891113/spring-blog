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

    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(name = "POST_ID", nullable = false)
    private Long postId;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "posting_id")
//    private Posting posting;

    public Comment(CommentRequestDto requestDto, String username, Long postId) {
        this.username = username;
        this.postId = postId;
        this.comment = requestDto.getComment();
    }

    public void update(CommentRequestDto responseDto) {
        this.comment = responseDto.getComment();
    }

    public boolean isCommentWriter(String username) {
        return username.equals(this.username);
    }
}
