package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.enums.UserRoleEnum;
import com.sparta.springblog.jwt.JwtUtil;
import com.sparta.springblog.requestdto.CommentRequestDto;
import com.sparta.springblog.responsedto.CommentResponseDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
import com.sparta.springblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    @PostMapping("/posts/{id}/comments")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        return commentService.create(id, requestDto, userDetails.getUsername());
    }

//    public boolean isAdmin(UserRoleEnum userRoleEnum) {
//        return userRoleEnum == UserRoleEnum.ADMIN;
//    }

    @PutMapping("/admin/posts/{postId}/comments/{id}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public CommentResponseDto updateCommentAdmin(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
//        if (!this.isAdmin(authenticatedUserInfoDto.getUserRoleEnum())) {
//            throw new IllegalArgumentException("권한이 없습니다.");
//        }
        return commentService.updateAdmin(id, requestDto);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        return commentService.update(id, requestDto, userDetails.getUsername());
    }

    @DeleteMapping("/admin/posts/{postId}/comments/{id}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseEntity<StatusResponseDto> deleteCommentAdmin(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "삭제 성공");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        responseDto.setStatus(StatusEnum.OK);
//        responseDto.setMessage("삭제 성공");

//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
//        if (!this.isAdmin(authenticatedUserInfoDto.getUserRoleEnum())) {
//            throw new IllegalArgumentException("권한이 없습니다.");
//        }
        commentService.deleteAdmin(id);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<StatusResponseDto> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "삭제 성공");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        responseDto.setStatus(StatusEnum.OK);
//        responseDto.setMessage("삭제 성공");

//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        commentService.delete(id, userDetails.getUsername());
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
