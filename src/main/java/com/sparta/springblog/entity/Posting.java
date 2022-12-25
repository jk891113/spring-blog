package com.sparta.springblog.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @OrderBy(value = "modifiedAt desc")
//    @JsonManagedReference
    @OneToMany(mappedBy = "posting", cascade = CascadeType.REMOVE, orphanRemoval = true)
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

    public boolean isPostingWriter(String username) {
        return username.equals(this.getUser().getUsername());
    }

    // JPA가 join 쿼리를 날려서 comment의 값들을 commentList에 자동으로 붙여 넣어주기 때문에 일부러 넣어줄 필요는 없다..
//    public void putCommentOnPosting(Comment comment) {
//        this.commentList.add(comment);
//    }
}
