package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.enums.UserRoleEnum;
import com.sparta.springblog.jwt.JwtUtil;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.requestdto.UsernameRequestDto;
import com.sparta.springblog.responsedto.AuthenticatedUserInfoDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
import com.sparta.springblog.service.BlogService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    @PostMapping("/posts")
    public PostingResponseDto createPosting(@RequestBody PostingRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        return blogService.createPosting(requestDto, authenticatedUserInfoDto.getUsername());

//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }
//        return blogService.createPosting(requestDto, claims);
//        } else {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
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

    @PutMapping("/posts/{id}")
    public PostingResponseDto updatePosting(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        AuthenticatedUserInfoDto authenticatedUserInfoDto = jwtUtil.validateAndGetUserInfo(token);
        if (this.isAdmin(authenticatedUserInfoDto.getUserRoleEnum())) {
            return blogService.updateAdmin(id, requestDto);
        } else {
            return blogService.update(id, requestDto, authenticatedUserInfoDto.getUsername());
        }

//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }
//            return blogService.update(id, requestDto, claims);
//        } else {
//            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
//        }
    }

    private boolean isAdmin(UserRoleEnum userRoleEnum) {
        return userRoleEnum == UserRoleEnum.ADMIN;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<StatusResponseDto> deletePosting(@PathVariable Long id, HttpServletRequest request) {
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
        blogService.deletePosting(id, claims);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }
}
