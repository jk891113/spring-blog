package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.enums.UserRoleEnum;
import com.sparta.springblog.dto.request.PostRequestDto;
import com.sparta.springblog.dto.request.UpdateRequestDto;
import com.sparta.springblog.dto.request.UsernameRequestDto;
import com.sparta.springblog.dto.response.PostResponseDto;
import com.sparta.springblog.dto.response.StatusResponseDto;
import com.sparta.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
//    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    @PostMapping("/posts")
    public PostResponseDto createPosting(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        return postService.createPosting(requestDto, userDetails.getUsername());
    }

//    @GetMapping("/posts")
//    public List<PostingResponseDto> getAllPostings() {
//        return blogService.getAllPostings();
//    }

    @GetMapping("/posts/id")
    public PostResponseDto getPostingById(@RequestParam Long id) {
        return postService.getPostingById(id);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getAllPostingsOrGetPostingByUsername(@RequestBody(required = false) UsernameRequestDto requestDto) {
        if (requestDto == null) {
            return postService.getAllPostings();
        } else {
            return postService.getPostingByUsername(requestDto);
        }
    }

    @GetMapping("/categories/{categoryId}/posts")
    public List<PostResponseDto> getAllPostingsByCategory(@PathVariable Long categoryId) {
        return postService.getAllPostingsByCategory(categoryId);
    }

//    private boolean isAdmin(UserRoleEnum userRoleEnum) {
//        return userRoleEnum == UserRoleEnum.ADMIN;
//    }

    @PutMapping("/admin/posts/{id}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public PostResponseDto updatePostingAdmin(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = new AuthenticatedUserInfoDto(userDetails.getRole(), userDetails.getUsername());
//        if (!this.isAdmin(authenticatedUserInfoDto.getUserRoleEnum())) {
//            throw new IllegalArgumentException("권한이 없습니다.");
//        }
        return postService.updateAdmin(id, requestDto);
    }

    @PutMapping("/posts/{id}")
    public PostResponseDto updatePosting(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        return postService.update(id, requestDto, userDetails.getUsername());
    }

    @DeleteMapping("/admin/posts/{id}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseEntity<StatusResponseDto> deletePostingAdmin(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
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
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = new AuthenticatedUserInfoDto(userDetails.getRole(), userDetails.getUsername());
//        if (!this.isAdmin(authenticatedUserInfoDto.getUserRoleEnum())) {
//            throw new IllegalArgumentException("권한이 없습니다.");
//        }
        postService.deletePostingAdmin(id);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<StatusResponseDto> deletePosting(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
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
        postService.deletePosting(id, userDetails.getUsername());
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
