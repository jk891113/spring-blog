package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.enums.UserRoleEnum;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.requestdto.UsernameRequestDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
import com.sparta.springblog.service.BlogService;
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
public class BlogController {
    private final BlogService blogService;
//    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    @PostMapping("/posts")
    public PostingResponseDto createPosting(@RequestBody PostingRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
//        String token = jwtUtil.resolveToken(request);
//
//        if (token == null) {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
//        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        return blogService.createPosting(requestDto, userDetails.getUsername());
    }

//    @GetMapping("/posts")
//    public List<PostingResponseDto> getAllPostings() {
//        return blogService.getAllPostings();
//    }

    @GetMapping("/posts/id")
    public PostingResponseDto getPostingById(@RequestParam Long id) {
        return blogService.getPostingById(id);
    }

    @GetMapping("/posts")
    public List<PostingResponseDto> getAllPostingsOrGetPostingByUsername(@RequestBody(required = false) UsernameRequestDto requestDto) {
        if (requestDto == null) {
            return blogService.getAllPostings();
        } else {
            return blogService.getPostingByUsername(requestDto);
        }
    }

    @GetMapping("/categories/{categoryId}/posts")
    public List<PostingResponseDto> getAllPostingsByCategory(@PathVariable Long categoryId) {
        return blogService.getAllPostingsByCategory(categoryId);
    }

//    private boolean isAdmin(UserRoleEnum userRoleEnum) {
//        return userRoleEnum == UserRoleEnum.ADMIN;
//    }

    @PutMapping("/admin/posts/{id}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public PostingResponseDto updatePostingAdmin(
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
        return blogService.updateAdmin(id, requestDto);
    }

    @PutMapping("/posts/{id}")
    public PostingResponseDto updatePosting(
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
        return blogService.update(id, requestDto, userDetails.getUsername());
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
        blogService.deletePostingAdmin(id);
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
        blogService.deletePosting(id, userDetails.getUsername());
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
