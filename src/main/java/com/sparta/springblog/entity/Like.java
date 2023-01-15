package com.sparta.springblog.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "POST_ID")
    private Long postId = 0L;

//    @OneToOne
//    @Column(name = "POST_ID")
//    private Post post;

    @Column(name = "COMMENT_ID")
    private Long commentId = 0L;

    @Column
    private long like = 0L;

    public void postLike(Long postId) {
        this.postId = postId;
    }

    public void commentLike(Long commentId) {
        this.commentId = commentId;
    }
}
