package com.sparta.springblog.service;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.enums.UserRoleEnum;
import com.sparta.springblog.jwt.JwtUtil;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.CommentRepository;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.requestdto.CommentRequestDto;
import com.sparta.springblog.responsedto.CommentResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> show(Long id) {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentResponseDto> responseDtoList = commentList.stream().map(comment -> new CommentResponseDto(comment)).collect(Collectors.toList());

        return responseDtoList;
    }

    @Transactional
    public CommentResponseDto create(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            Posting posting = blogRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 포스팅이 존재하지 않습니다.")
            );

            Comment comment = new Comment(requestDto, user, posting);
            commentRepository.save(comment);
            return new CommentResponseDto(comment);
        } else {
            return null;
        }
    }

    @Transactional
    public CommentResponseDto update(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 포스팅이 존재하지 않습니다.")
            );

            UserRoleEnum userRoleEnum = user.getRole();
            if (!user.getUsername().equals(comment.getUser().getUsername()) && userRoleEnum == UserRoleEnum.USER) {
                throw new IllegalArgumentException("본인이 작성한 댓글만 수정할 수 있습니다.");
            }
            comment.update(requestDto);
            CommentResponseDto responseDto = new CommentResponseDto(comment);
            return responseDto;
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 포스팅이 존재하지 않습니다.")
            );

            UserRoleEnum userRoleEnum = user.getRole();
            if (!user.getUsername().equals(comment.getUser().getUsername()) && userRoleEnum == UserRoleEnum.USER) {
                throw new IllegalArgumentException("본인이 작성한 댓글만 수정할 수 있습니다.");
            }
            comment.update(requestDto);
            CommentResponseDto responseDto = new CommentResponseDto(comment);
    }
}
