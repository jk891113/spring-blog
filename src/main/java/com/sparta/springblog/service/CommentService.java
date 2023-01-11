package com.sparta.springblog.service;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.CommentRepository;
import com.sparta.springblog.requestdto.CommentRequestDto;
import com.sparta.springblog.responsedto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public List<CommentResponseDto> getChildrenCommentList(Long commentId) {
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        List<Comment> childrenCommentList = commentRepository.getAllByParentIdOrderByModifiedAtDesc(commentId);
        List<CommentResponseDto> responseDtoList = childrenCommentList.stream()
                .map(comment -> new CommentResponseDto(comment))
                .collect(Collectors.toList());
        return responseDtoList;
    }

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, String username) {
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
        Posting posting = blogRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 포스팅이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto, username, postId);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto createChildrenComment(Long postId, Long parentId, CommentRequestDto requestDto, String username) {
        Comment parentComment = commentRepository.findById(parentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(parentId, requestDto, username);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateAdmin(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto update(Long commentId, CommentRequestDto requestDto, String username) {
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (!comment.isCommentWriter(username)) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteAdmin(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void delete(Long commentId, String username) {
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (!comment.isCommentWriter(username)) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
