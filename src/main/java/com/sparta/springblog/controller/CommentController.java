package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.jwt.JwtUtil;
import com.sparta.springblog.requestdto.CommentRequestDto;
import com.sparta.springblog.responsedto.CommentResponseDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
import com.sparta.springblog.service.CommentService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            return commentService.create(id, requestDto, claims);
        } else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            return commentService.update(id, requestDto, claims);
        } else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<StatusResponseDto> deleteComment(@PathVariable Long id, HttpServletRequest request) {
        StatusResponseDto responseDto = new StatusResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseDto.setStatus(StatusEnum.OK);
        responseDto.setMessage("삭제 성공");

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            commentService.delete(id, claims);
            return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }
}