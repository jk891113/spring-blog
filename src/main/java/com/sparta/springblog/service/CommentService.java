package com.sparta.springblog.service;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.CommentRepository;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.requestdto.CommentRequestDto;
import com.sparta.springblog.responsedto.CommentResponseDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    @Transactional
    public CommentResponseDto create(Long id, CommentRequestDto requestDto, Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 포스팅이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto, user, posting);
        commentRepository.save(comment);
//        posting.putCommentOnPosting(comment);
        return new CommentResponseDto(comment);
    }


    @Transactional
    public CommentResponseDto update(Long id, CommentRequestDto requestDto, Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (user.isCommentWriter(comment)) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void delete(Long id, Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (user.isCommentWriter(comment)) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }
        commentRepository.deleteById(id);
    }
}
