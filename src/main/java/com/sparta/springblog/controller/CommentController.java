package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.requestdto.CommentRequestDto;
import com.sparta.springblog.responsedto.CommentResponseDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
import com.sparta.springblog.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService  commentService;

    // 테스트
    @GetMapping("/comment/{id}")
    public List<CommentResponseDto> showComment(@PathVariable Long id) {
        return commentService.show(id);
    }

    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.create(id, requestDto, request);
    }

    @PutMapping("/comment{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.update(id, requestDto, request);
    }

    @DeleteMapping("/comment{id}")
    public ResponseEntity<StatusResponseDto> deleteComment(@PathVariable Long id, HttpServletRequest request) {
        StatusResponseDto responseDto = new StatusResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseDto.setStatus(StatusEnum.OK);
        responseDto.setMessage("삭제 성공");

        commentService.delete(id, request);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
