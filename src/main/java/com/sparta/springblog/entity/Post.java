package com.sparta.springblog.entity;

import com.sparta.springblog.dto.request.PostRequestDto;
import com.sparta.springblog.dto.request.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long categoryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

//    @OrderBy(value = "modifiedAt desc")
////    @JsonManagedReference
//    @OneToMany(mappedBy = "posting", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto requestDto, User user) {
        this.user = user;
        this.categoryId = requestDto.getCategoryId();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(UpdateRequestDto requestDto) {
        this.categoryId = requestDto.getCategoryId();
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
