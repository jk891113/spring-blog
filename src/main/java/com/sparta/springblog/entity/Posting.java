package com.sparta.springblog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Posting extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OrderBy(value = "modifiedAt desc")
    @JsonManagedReference
    @OneToMany(mappedBy = "posting")
    private List<Comment> commentList = new ArrayList<>();

    public Posting(PostingRequestDto requestDto, User user) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(UpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void putCommentOnPosting(Comment comment) {
        this.commentList.add(comment);
    }
}
